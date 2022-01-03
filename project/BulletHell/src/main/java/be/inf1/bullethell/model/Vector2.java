package be.inf1.bullethell.model;

/**
 *
 * @author u0143348
 */
public class Vector2 {
    // we make the conscious choice to keep these public here
    // for this type of simple Model, I don't want to be using getters/setters everywhere
    public int x;
    public int y;
    
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }
    
    public Vector2( int x, int y ) {
        this.x = x;
        this.y = y;
    }
}
