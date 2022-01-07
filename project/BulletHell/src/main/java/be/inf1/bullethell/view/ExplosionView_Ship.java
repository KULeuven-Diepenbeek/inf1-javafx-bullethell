/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.GameLoop;
import be.inf1.bullethell.ImageController;
import be.inf1.bullethell.model.LoadedImage;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        Image image = ImageController.getImage(LoadedImage.Type.EXPLOSION_1);
        
        // see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
        ImageView iv = new ImageView( image );
        iv.setFitWidth( 120 );
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        
        this.getChildren().add(iv);
        
        this.setTranslateX( position.x - 60 );
        this.setTranslateY( position.y - 60 );
        
        this.update();
    }
    
    @Override
    public Region getView() {
        return this;
    }
    
    @Override
    public boolean update(){
        
        this.duration++;
        
        if ( this.duration > GameLoop.FRAMES_PER_SECOND ) { // show for 1 second, which is 30 frames' worth of updates
            return false;
        }
        
        return true;
    }
}
