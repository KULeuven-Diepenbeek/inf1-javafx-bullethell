package be.inf1.bullethell.model;

import javafx.scene.paint.Color;

/**
 *
 * @author u0143348
 */
public class Player extends Spaceship {
    public static int MAX_HEALTH = 5;
    
    private String name;
    private Color color;
    
    public Player(String name, int health, Color color, Vector2 position) {
        super(health, position);
        
        this.getDirection().x = 1; // players typically aim to the right of the screen
        
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
