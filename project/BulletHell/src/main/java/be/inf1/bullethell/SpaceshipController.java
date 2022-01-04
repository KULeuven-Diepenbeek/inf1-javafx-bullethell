package be.inf1.bullethell;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Spaceship;
import be.inf1.bullethell.model.SpaceshipInput;
import be.inf1.bullethell.model.Vector2;
import be.inf1.bullethell.view.BulletView;
import be.inf1.bullethell.view.SpaceshipView;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author u0143348
 */
public class SpaceshipController {
    private Spaceship ship;
    private SpaceshipView view;
    private SpaceshipInput input;
    private ArrayList<BulletView> bullets; 
    
    private ArrayList<Bullet> bulletsWaitingForViews;
    
    private AnchorPane container;
    
    public SpaceshipController(Spaceship ship, SpaceshipView view, SpaceshipInput input, AnchorPane container) {
        this.ship = ship;
        this.view = view;
        this.input = input;
        this.container = container;
        
        this.bullets = new ArrayList<BulletView>();
        this.bulletsWaitingForViews = new ArrayList<Bullet>();
    }
    
    public void shoot() {
        Bullet b = new Bullet( new Vector2(this.ship.getPosition().x, this.ship.getPosition().y), 1 );
        // shoot in the direction you're facing
        b.getVelocity().x = this.ship.getDirection().x * 10;
        b.getVelocity().y = this.ship.getDirection().y * 10;
        
        // we can't just create and add BulletViews here, sadly...
        // this because input handling is done in the Timer thread,
        // while UI updates can only be done in the JavaFX thread
        // so we keep a list of Bullets without views and add those later in updateViews()
        this.bulletsWaitingForViews.add( b );
    }
    
    public void updateModels() {
        
        // the ship itself
        this.input.apply(this.ship); // this only changes velocities, need to handle firing logic separately
        if ( this.input.fire() ) {
            this.shoot();
        }
        
        this.ship.update();
        
        // the bullets
        for ( BulletView bv : this.bullets ) {
            bv.getModel().update();
        }
    }
    
    public void updateViews() {
        this.view.update();
        
        for ( Bullet b : this.bulletsWaitingForViews ) {
        
            BulletView bv = new BulletView(b);
            bv.setup();

            bullets.add( bv );
            this.container.getChildren().add( bv );
        }
        
        this.bulletsWaitingForViews.clear();
        
        for ( BulletView bv : this.bullets ) {
            bv.update();
        }
    }
    
    public Spaceship getShip() {
        return ship;
    }

    public SpaceshipView getView() {
        return view;
    }

    public SpaceshipInput getInput() {
        return input;
    }
}
