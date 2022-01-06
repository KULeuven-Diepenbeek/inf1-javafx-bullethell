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
    
    private BulletGenerator bulletGenerator;
    
    public SpaceshipController(Spaceship ship, SpaceshipView view, SpaceshipInput input, BulletGenerator bulletGenerator) {
        this.ship = ship;
        this.view = view;
        this.input = input;
        
        this.bulletGenerator = bulletGenerator;
    }
    
    public void updateModels() {
        
        // the ship itself
        this.input.apply(this.ship); // this only changes velocities, need to handle firing logic separately
        if ( this.input.fire() ) {
            this.bulletGenerator.shootBullet(this);
        }
        
        this.ship.update();
    }
    
    public void updateViews() {
        this.view.update();
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
    
    public BulletGenerator getBulletGenerator() {
        return this.bulletGenerator;
    }
}
