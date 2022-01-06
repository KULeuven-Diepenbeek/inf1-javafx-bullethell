/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.GameLoop;
import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Collidable;
import be.inf1.bullethell.model.Collision;
import be.inf1.bullethell.model.Enemy;
import be.inf1.bullethell.model.Player;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author u0143348
 */
public class BulletView_SinRocket extends BulletView {  
    private static int WIDTH = 20;
    private static int HEIGHT = 20;
    
    private static int HALF_WIDTH = WIDTH / 2;
    private static int HALF_HEIGHT = HEIGHT / 2;
    
    private int updatesSinceDirectionSwitch = 0;

    public BulletView_SinRocket(Bullet model) {
        super(model);
        
        this.updatesSinceDirectionSwitch = 0;
    }
    
    @Override
    public void setup() {
        // TODO: this is just a placeholder, want to replace with an actual image later on
        Polygon p = new Polygon();
        // going left
        if ( this.model.getVelocity().x <= 0 ) {
            p.getPoints().addAll(new Double[]{
                0.0, (double) HALF_HEIGHT,
                (double) WIDTH, 0.0,
                (double) WIDTH, (double) HEIGHT });
        }
        // going right
        else {
            p.getPoints().addAll(new Double[]{
                0.0, 0.0,
                (double) WIDTH, (double) HALF_HEIGHT,
                0.0, (double) WIDTH });
        }
        
        p.setFill( Color.PURPLE );
        this.getChildren().add(p);
        
        this.update();
    }
    
    // supposed to be called once per game loop tick
    @Override
    public void update() {
        super.update();
        
        // reverse vertical velocity every x ms, creating a sinus-oid movement
        if ( this.updatesSinceDirectionSwitch > GameLoop.FRAMES_PER_SECOND ) {
        
            this.model.getVelocity().y = -1 * this.model.getVelocity().y;
            
            this.updatesSinceDirectionSwitch = 0;
        }
        else {
            ++this.updatesSinceDirectionSwitch;
        }
    }
    
    protected int getBulletWidth() {
        return BulletView_SinRocket.WIDTH;
    }
    
    protected int getBulletHeight() {
        return BulletView_SinRocket.HEIGHT;
    }
    
    @Override
    public Vector2 getCenterPoint() {
        return new Vector2( this.model.getPosition().x + BulletView_SinRocket.HALF_WIDTH, this.model.getPosition().y + BulletView_SinRocket.HALF_HEIGHT );
    }
    
    @Override
    public Explosion createExplosion() {
        return new ExplosionView_Bullet();
    }
}
