/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.GameLoop;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 *
 * @author u0143348
 */
public class ExplosionView_Ship extends Explosion {
    
    private static int WIDTH = 50;
    private static int HEIGHT = 50;
    
    private int duration = 0;
    private Circle circle;
    
    public ExplosionView_Ship() {
        
    }
        
    @Override
    public void setup(Vector2 position) {
        // TODO: this is just a placeholder, want to replace with an actual image later on
        this.circle = new Circle(0, 0, HEIGHT / 2);
        this.circle.setFill( Color.BLACK );
        this.getChildren().add(this.circle);
        
        this.setTranslateX( position.x );
        this.setTranslateY( position.y );
        
        this.update();
    }
    
    @Override
    public Region getView() {
        return this;
    }
    
    @Override
    public boolean update(){
        
        this.duration++;
        
        // halfway through, change explosion color to RED
        if ( this.duration == Math.round(GameLoop.FRAMES_PER_SECOND / 2) ) {
            this.circle.setFill( Color.RED );
        }
        
        if ( this.duration > GameLoop.FRAMES_PER_SECOND ) { // show for 1 second, which is 30 frames' worth of updates
            return false;
        }
        
        return true;
    }
}
