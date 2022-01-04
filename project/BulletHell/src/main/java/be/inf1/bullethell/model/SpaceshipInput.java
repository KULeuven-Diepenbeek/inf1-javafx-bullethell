/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class SpaceshipInput {
    protected Boolean currentUp;
    protected Boolean currentDown;
    protected Boolean currentLeft;
    protected Boolean currentRight;
    protected Boolean currentFire;
    
    public SpaceshipInput() {
        this.currentUp = false;
        this.currentDown = false;
        this.currentLeft = false;
        this.currentRight = false;
        this.currentFire = false;
    }
    
    // getters, but without the "get" because that would be boring to write quite quickly
    public Boolean up() { return this.currentUp; }
    public Boolean down() { return this.currentDown; }
    public Boolean left() { return this.currentLeft; }
    public Boolean right() { return this.currentRight; }
    public Boolean fire() { 
        // for shooting, we only want to shoot once for each key press, 
        // so reset to false every time this is read
        Boolean hadFired = this.currentFire;
        this.currentFire = false;
        
        return hadFired;
    }
    
    public void apply(Spaceship ship) {
        
        Vector2 velocity = ship.getVelocity(); // change the velocity directly instead of setting a new Vector2 every time
        
        // reset velocity to make input really snappy! 
        // if player stops pressing button, movement in that directly immediately stops (no momentum for now)
        velocity.x = 0;
        velocity.y = 0;

        if ( this.up() ) {
            velocity.y = -1;
        }
        else if ( this.down() ) {
            velocity.y = 1;
        }

        if ( this.left() ) {
            velocity.x = -1;
        }
        else if ( this.right() ) {
            velocity.x = 1;
        }
    }
}
