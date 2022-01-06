package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Collidable;
import be.inf1.bullethell.model.Spaceship;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.layout.Region;

/**
 *
 * @author u0143348
 */
public abstract class SpaceshipView extends Region implements Collidable {
    private Spaceship model;
    
    public SpaceshipView(Spaceship model) {
        this.model = model;
    }
    
    public abstract void setup();
    
    // supposed to be called once per game loop tick
    public void update() {
        this.setTranslateX( this.model.getPosition().x );
        this.setTranslateY( this.model.getPosition().y );
    }
    
    @Override
    public boolean collidesWith(Collidable c) {
        Vector2 testPoint = c.getCenterPoint();
        
        return this.collidesWith( testPoint );
    }
}
