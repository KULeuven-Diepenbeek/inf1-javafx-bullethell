/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class Spaceship {
    private int health;
    private Vector2 position;
    private Vector2 velocity; // current velocity
    private Vector2 direction;
    
    // you could see this as speed/velocity modifiers. Normal velocity changes by some quantity each frame. This can be used to make that faster/slower
    private Vector2 enginePower;
    
    public Spaceship(int health, Vector2 position) {
        this.health = health;
        this.position = position;
        this.velocity = new Vector2(0,0);
        this.direction = new Vector2(-1,0); // assume most spaceship are facing to the left
        this.enginePower = new Vector2(1,1);
    }
    
    public void update() {
        this.position.x += this.velocity.x * this.enginePower.x;
        this.position.y += this.velocity.y * this.enginePower.y;
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    public Vector2 getDirection() {
        return direction;
    }

    public void getDirection(Vector2 direction) {
        this.direction = direction;
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    
    public Vector2 getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Vector2 enginePower) {
        this.enginePower = enginePower;
    }
    
}
