package be.inf1.bullethell;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.view.BulletView;
import be.inf1.bullethell.view.BulletView_SinRocket;

/**
 *
 * @author u0143348
 */
public class BulletGenerator_SinRocket extends BulletGenerator {
       
    public BulletGenerator_SinRocket(BulletController controller) {
        super(controller);
    }
    
    @Override
    public Bullet createBullet(){
        Bullet b = new Bullet(1, 10); // 1 damage, 10 horizontal speed
        b.getVelocity().y = 2; // sinRockets have some vertical movement!
        
        return b;
    }
    
    @Override
    public BulletView createBulletView(Bullet bullet) {
        return new BulletView_SinRocket( bullet );
    }
}
