package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class Bullet {
    private Vector2 position;
    private Vector2 velocity;
    
    private int damage;
    
    public Bullet(Vector2 position, int damage) {
        this.position = position;
        this.damage = damage;
        
        this.velocity = new Vector2(0,0);
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    
    public void update() {
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;
    }
}
