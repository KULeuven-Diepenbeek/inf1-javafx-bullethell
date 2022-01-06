package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class Bullet {
    private Spaceship shooter;
    private Vector2 position;
    private Vector2 velocity;
    
    private int damage;
    private int speed; // primarily used to manipulate the initial velocity
    
    public Bullet(Spaceship shooter, Vector2 position, int damage, int speed) {
        this.shooter = shooter;
        this.position = position;
        this.damage = damage;
        this.speed = speed;
        
        this.velocity = new Vector2(speed,0);
    }
    
    public Bullet(int damage, int speed) {
        this.damage = damage;
        this.speed = speed;
        
        // expected to be set later
        this.shooter = null;
        this.position = new Vector2(0,0);
        this.velocity = new Vector2(speed,0);
    }
    
    public Vector2 getPosition() {
        return position;
    }
    
    public void setPosition( Vector2 position ) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setShooter(Spaceship shooter) {
        this.shooter = shooter;
    }
    
    public Spaceship getShooter() {
        return shooter;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void update() {
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;
    }
}
