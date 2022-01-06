package be.inf1.bullethell.model;

import be.inf1.bullethell.model.Vector2;
import be.inf1.bullethell.view.Explosion;

/**
 *
 * @author u0143348
 */
public interface Collidable {
    public Vector2 getCenterPoint();
    
    public boolean collidesWith(Collidable c);
    public boolean collidesWith(Vector2 point);
    
    public Explosion createExplosion();
}
