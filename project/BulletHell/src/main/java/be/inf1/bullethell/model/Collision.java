package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class Collision {
    private Collidable hittee; // the object that gets hit (for example spaceship)
    private Collidable hitter; // the object that hits the hittee (for example bullet)

    private boolean destroyHittee;
    private boolean destroyHitter;
    
    private Vector2 position;

    public Collision(Vector2 position, Collidable hittee, Collidable hitter, boolean destroyHittee, boolean destroyHitter) {
        this.position = position;
        
        this.hittee = hittee;
        this.hitter = hitter;
        
        this.destroyHittee = destroyHittee;
        this.destroyHitter = destroyHitter;
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public Collidable getHittee() {
        return hittee;
    }

    public Collidable getHitter() {
        return hitter;
    }
    
    public boolean destroyHittee() {
        return destroyHittee;
    }

    public boolean destroyHitter() {
        return destroyHitter;
    }
}
