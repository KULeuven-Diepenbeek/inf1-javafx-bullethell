/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.ImageController;
import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Collidable;
import be.inf1.bullethell.model.Collision;
import be.inf1.bullethell.model.Enemy;
import be.inf1.bullethell.model.LoadedImage;
import be.inf1.bullethell.model.Player;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author u0143348
 */
public class BulletView_Laser extends BulletView {
    private static int WIDTH = 54;
    private static int HEIGHT = 13;
    
    private static int HALF_WIDTH = WIDTH / 2;
    private static int HALF_HEIGHT = HEIGHT / 2;

    public BulletView_Laser(Bullet model) {
        super(model);
    }
    
    public void setup() {
        // re-enable this code to show the "hitbox"
//        Rectangle l = new Rectangle( 0, 0, BulletView_Laser.WIDTH, BulletView_Laser.HEIGHT);
//        l.setFill( Color.CYAN );
//        this.getChildren().add(l);
        
        Image image = ImageController.getImage(LoadedImage.Type.BULLET_LASER);
        ImageView iv = new ImageView( image );
        // the lasers are facing UP, we want them facing left or right
        if ( this.model.getShooter().getDirection().x >= 0 ) {
            // facing RIGHT
            iv.setRotate(90); 
            iv.setY( -20 ); // make the laser visually fit the hitbox a bit better
            iv.setX( 20 );
        }
        else {
            // facing LEFT
            iv.setRotate(-90);
            iv.setY( -20 ); // make the laser visually fit the hitbox a bit better
            iv.setX( 20 ); 
        }
        iv.setFitWidth( 13 );
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        
        
        this.getChildren().add(iv);
        
        this.update();
        
        this.update();
    }
    
    protected int getBulletWidth() {
        return BulletView_Laser.WIDTH;
    }
    
    protected int getBulletHeight() {
        return BulletView_Laser.HEIGHT;
    }
        
    @Override
    public Vector2 getCenterPoint() {
        return new Vector2( this.model.getPosition().x + BulletView_Laser.HALF_WIDTH, this.model.getPosition().y + BulletView_Laser.HALF_HEIGHT );
    }
}
