/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author u0143348
 */
public class KeyboardInput {
    private KeyCode up;
    private KeyCode down;
    private KeyCode left;
    private KeyCode right;
    private KeyCode fire;
    
    private Boolean currentUp;
    private Boolean currentDown;
    private Boolean currentLeft;
    private Boolean currentRight;
    private Boolean currentFire;
    
    public KeyboardInput(KeyCode up, KeyCode down, KeyCode left, KeyCode right, KeyCode fire) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.fire = fire;
        
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
    
    public void handleKeyPress( KeyEvent e ) {
        KeyCode code = e.getCode();
        
        if ( code == this.up ) {
            this.currentUp = true;
        }
        else if ( code == this.down ) {
            this.currentDown = true;
        }
        else if ( code == this.left ) {
            this.currentLeft = true;
        }
        else if ( code == this.right ) {
            this.currentRight = true;
        }
        else if ( code == this.fire ) {
            this.currentFire = true;
        }
    }
    
    public void handleKeyRelease( KeyEvent e ) {
        KeyCode code = e.getCode();
        
        if ( code == this.up ) {
            this.currentUp = false;
        }
        else if ( code == this.down ) {
            this.currentDown = false;
        }
        else if ( code == this.left ) {
            this.currentLeft = false;
        }
        else if ( code == this.right ) {
            this.currentRight = false;
        }
        
        // Note: firing is a special case that's handled in the fire() getter above
    }
}
