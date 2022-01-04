package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class AIInput_UpDown extends SpaceshipInput {
    
    private int ticksSinceLastShot;
    
    public AIInput_UpDown() {
        this.currentDown = true;
        this.ticksSinceLastShot = 0;
    }
    
    @Override 
    public void apply(Spaceship ship) {
        // we want the ship to move Vertically across the screen,
        // shooting at a certain interval
        
        if ( ship.getPosition().y > 650 ) { // nearing the bottom of the screen
            this.currentDown = false;
            this.currentUp = true;
        }
        else if ( ship.getPosition().y < 10 ) { // nearing the top of the screen
            this.currentDown = true;
            this.currentUp = false;
        }
        
        // shoot once every 20 ticks/~600ms
        if ( this.ticksSinceLastShot > 20 ){ 
            this.ticksSinceLastShot = 0;
            
            this.currentFire = true;
        }
        else {
            this.ticksSinceLastShot += 1;
        }
        
        super.apply(ship);
    }
}
