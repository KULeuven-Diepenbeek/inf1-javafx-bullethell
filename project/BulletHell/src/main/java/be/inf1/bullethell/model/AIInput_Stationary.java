/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class AIInput_Stationary extends SpaceshipInput {
    private int ticksSinceLastShot;
    
    public AIInput_Stationary() {
        this.ticksSinceLastShot = 0;
    }
    
    @Override 
    public void apply(Spaceship ship) {
        // we want the ship to remain stationary
        // shooting every second
        
        if ( this.ticksSinceLastShot > 30 ){ 
            this.ticksSinceLastShot = 0;
            
            this.currentFire = true;
        }
        else {
            this.ticksSinceLastShot += 1;
        }
        
        super.apply(ship);
    }
}
