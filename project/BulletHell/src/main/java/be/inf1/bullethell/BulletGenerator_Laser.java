package be.inf1.bullethell;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.view.BulletView;
import be.inf1.bullethell.view.BulletView_Laser;

/**
 *
 * @author u0143348
 */
public class BulletGenerator_Laser extends BulletGenerator {

    public BulletGenerator_Laser(BulletController controller) {
        super(controller);
    }
    
    @Override
    public Bullet createBullet(){
        return new Bullet(1, 20); // 1 damage, 20 speed
    }
    
    @Override
    public BulletView createBulletView(Bullet bullet) {
        return new BulletView_Laser( bullet );
    }
}