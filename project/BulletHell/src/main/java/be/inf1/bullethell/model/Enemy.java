package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class Enemy extends Spaceship {
    
    public Enemy(int health, Vector2 position) {
        super(health, position);
        
        this.getDirection().x = -1; // enemies typically the screen on the right, aiming left
    }
}
