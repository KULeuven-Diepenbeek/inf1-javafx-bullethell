/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.App;
import be.inf1.bullethell.ImageController;
import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.LoadedImage;
import be.inf1.bullethell.model.Player;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author u0143348
 */
public class BackgroundView extends Region {

    private Vector2 position;
    
    public BackgroundView() {
        this.position = new Vector2(0,0);
        this.setup();
    }

    public void setup() {
        
        Image image = ImageController.getImage(LoadedImage.Type.BACKGROUND_1);
        
        // see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
        ImageView iv = new ImageView( image );
        iv.setFitWidth( App.WINDOW_WIDTH * 2 );
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        
        this.getChildren().add(iv);
        
        this.update();
    }
    
    public void update() {
        // we keep moving the background to the left every tick
        // when it has moved a full screen, we need to reset, otherwise we would run out of image to show!
        // This is because the background image is only twice as wide as our level 
        if ( this.position.x < -1 * App.WINDOW_WIDTH ) {
            this.position.x = 0;
        }
        else {
            this.position.x -= 1;
        }
        
        this.setTranslateX( this.position.x );
    }
}
