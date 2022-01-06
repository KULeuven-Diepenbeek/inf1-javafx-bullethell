/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Collidable;
import be.inf1.bullethell.model.Collision;
import be.inf1.bullethell.model.Spaceship;
import be.inf1.bullethell.model.Vector2;
import be.inf1.bullethell.view.BulletView;
import be.inf1.bullethell.view.Explosion;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author u0143348
 */
public class BulletController {
    private ArrayList<BulletView> bullets; 
    private ArrayList<Explosion> explosions;
    
    private ArrayList<SpaceshipController> shootersWaitingForViews;
    private ArrayList<BulletView> bulletsToRemove;
    
    private AnchorPane container;
    
    public BulletController(AnchorPane container) {
        this.bullets = new ArrayList<BulletView>();
        this.explosions = new ArrayList<Explosion>();
        
        this.shootersWaitingForViews = new ArrayList<SpaceshipController>();
        this.bulletsToRemove = new ArrayList<BulletView>();
        
        this.container = container;
    }
    
    public void shootBullet(SpaceshipController shooter) {
        
        // we can't just create and add BulletViews here, sadly...
        // this because input handling/shooting is done in the Timer thread,
        // while UI updates can only be done in the JavaFX thread
        // we also can't make the bullets here and add just views later, because we need information in the SpaceshipController.getView()
        // to create the correct type of BulletView...
        
        // So we keep a list of SpaceshipControllers that shot and actually have them shoot later in updateViews()
        // We need to make this thread safe, because when we have many things shooting bullets,
        // we can and do get ConcurrentModificationException when looping over this in updateViews() to actually make the Bullets
        // see also: https://stackoverflow.com/questions/1431681/correct-way-to-synchronize-arraylist-in-java
        synchronized(this.shootersWaitingForViews){
            this.shootersWaitingForViews.add( shooter );
        }
    }
    
    public void updateModels() {
        // update the bullet models: adjust coordinates
        synchronized(this.bullets){
            for ( BulletView bv : this.bullets ) {
                bv.getModel().update();
            }
        }
    }
    
    public void updateViews() {
        
        synchronized(this.bullets){
            // make view for recently fired bullets
            synchronized(this.shootersWaitingForViews){

                for ( SpaceshipController shipController : this.shootersWaitingForViews ) {

                    Bullet b = shipController.getBulletGenerator().createBullet();
                    b.setShooter( shipController.getShip() );
                    b.setPosition( shipController.getView().getCenterPoint() );
                    
                    // shoot in the direction the shooter is facing
                    b.getVelocity().x = b.getVelocity().x * shipController.getShip().getDirection().x;
                    b.getVelocity().y = b.getVelocity().y;

                    BulletView bv = shipController.getBulletGenerator().createBulletView( b );
                    bv.setup();

                    bullets.add( bv );
                    this.container.getChildren().add( bv );
                }

                this.shootersWaitingForViews.clear();
            }

            // update views for existing bullets
            for ( BulletView bv : this.bullets ) {
                bv.update();
                this.checkBulletOffscreen( bv );
            }

            // clean up bullets that were marked as removable in this tick
            for ( BulletView toRemove : this.bulletsToRemove ) {
                // remove both from the local list, as from the actual JavaFX container
                this.bullets.remove( toRemove );
                this.container.getChildren().remove( toRemove );
            }
            this.bulletsToRemove.clear();
        
        }
        
        // update views for explosions
        ArrayList<Explosion> explosionsToRemove = new ArrayList<Explosion>();
        for ( Explosion e : this.explosions ) {
            if ( !e.update() ) {
                // if update returns false, the explosion is done : mark it for removal
                explosionsToRemove.add(e);
            }
        }
        
        for ( Explosion toRemove : explosionsToRemove ) {
            // remove both from the local list, as from the actual JavaFX container
            this.explosions.remove( toRemove );
            this.container.getChildren().remove( toRemove );
        }
    }
    
    // should only be called on the UI thread, as it creates Explosions!
    // Ideally, we'd split this up and have this create separate Collision objects
    // that then get interpreted on the UI thread, but we'll leave that as an exercise for the reader ;) 
    public void calculateCollisions( ArrayList<SpaceshipController> ships ) {
        
        ArrayList<Spaceship> shipModels = new ArrayList<Spaceship>();
        for ( SpaceshipController shipController : ships ) {
            shipModels.add( shipController.getShip() );
        }
        
        for ( SpaceshipController shipController : ships ) {
            
            for ( BulletView bullet : this.bullets ) {
                
                // we want bullets shot by enemies only to hit players and vice versa
                // so check if the original shooter is not one of the current collection
                // TODO: add proper "grouping" logic here by using tags or similar
                boolean shootingOurselves = shipModels.indexOf( bullet.getModel().getShooter() ) >= 0;
                
                if ( !shootingOurselves && 
                     shipController.getView().collidesWith(bullet) ) {
                    // we have a hit! 
                    // now we need to do a few things:
                    
                    // 1. reduce player health (dealing with deaths is done in the GameViewController)
                    int currentHealth = shipController.getShip().getHealth();
                    currentHealth -= bullet.getModel().getDamage();
                    shipController.getShip().setHealth(currentHealth);
                    
                    // 2. remove Bullet from the game
                    this.bulletsToRemove.add( bullet );
                    
                    // 3. show explosions
                    // the bullet always explodes
                    this.createExplosion( bullet.getCenterPoint(), bullet );
                    // the ship explodes only if it has low health
                    // actually removing the ship etc. is done in the GameViewController
                    if ( currentHealth <= 0 ) {
                        this.createExplosion( shipController.getView().getCenterPoint(), shipController.getView() );
                    }
                }
            }
        }
    }
    
    private void createExplosion( Vector2 position, Collidable exploder ) {
        Explosion e = exploder.createExplosion();
        e.setup( position );
        
        this.container.getChildren().add( e.getView() );
        e.update();
        
        this.explosions.add( e );
    }
    
    private void checkBulletOffscreen( BulletView bullet ) {
    
        // we want to remove bullets that go off-screen without hitting anything
        if ( bullet.getModel().getPosition().x < -500 ) {
            this.bulletsToRemove.add( bullet );
        }
        else if ( bullet.getModel().getPosition().x > App.WINDOW_WIDTH + 500 ) {
            this.bulletsToRemove.add( bullet );
        }
        else if ( bullet.getModel().getPosition().y < - 500 ) {
            this.bulletsToRemove.add( bullet );
        }
        else if ( bullet.getModel().getPosition().x > App.WINDOW_HEIGHT + 500 ) {
            this.bulletsToRemove.add( bullet );
        }
    }
}
