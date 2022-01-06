package be.inf1.bullethell;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.view.BulletView;

/**
 *
 * @author u0143348
 */
public abstract class BulletGenerator {
    
    private BulletController controller;
    
    public BulletGenerator(BulletController controller) {
        this.controller = controller;
    }
    
    public void shootBullet( SpaceshipController shipController ) {
        this.controller.shootBullet(shipController);
    }
    
    public abstract Bullet createBullet();
    public abstract BulletView createBulletView(Bullet bullet);
}
