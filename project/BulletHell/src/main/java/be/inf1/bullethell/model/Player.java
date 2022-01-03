package be.inf1.bullethell.model;

import javafx.scene.paint.Color;

/**
 *
 * @author u0143348
 */
public class Player {
    public static int MAX_HEALTH = 5;
    
    private String name;
    private int health;
    private Color color;
    private Vector2 position;
    private Vector2 velocity;
    
    public Player(String name, int health, Color color, Vector2 position) {
        this.name = name;
        this.health = health;
        this.color = color;
        this.position = position;
        this.velocity = new Vector2(0,0);
    }

    public Player(Vector2 position) {
        this.position = position; 
    }
    
    public void update() {
        this.position.x += this.velocity.x;
        this.position.y += this.velocity.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
