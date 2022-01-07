/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

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
public class PlayerAvatarView extends SpaceshipView {
    private Player model;
    
    private static int WIDTH = 60;
    private static int HEIGHT = 75;

    public PlayerAvatarView(Player model) {
        super(model);
        
        this.model = model;
        this.setup();
    }

    public Player getModel() {
        return model;
    }
    
    public void setup() {
  
        // re-enable this code to show the "hitbox"
//         Rectangle r = new Rectangle( 0, 0, PlayerAvatarView.WIDTH, PlayerAvatarView.HEIGHT);
//         r.setFill( this.model.getColor() );
//         this.getChildren().add(r);
        
        Image image = null;
        if ( this.model.getColor() == Color.GREEN ) {
            image = ImageController.getImage(LoadedImage.Type.PLAYER_GREEN);
        }
        else {
            image = ImageController.getImage(LoadedImage.Type.PLAYER_BLUE);
        }
        
        // see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
        ImageView iv = new ImageView( image );
        iv.setRotate(90); // the player images are facing UP by default, we want them facing RIGHT 
        iv.setFitWidth( 99 ); // we don't use PlayerAvatarView.WIDTH because we want the hitbox to be a bit smaller than the actual image
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        
        this.getChildren().add(iv);
        
        this.update();
    }
    
    @Override
    public Vector2 getCenterPoint() {
        return new Vector2( this.model.getPosition().x + PlayerAvatarView.WIDTH / 2, this.model.getPosition().y + PlayerAvatarView.HEIGHT / 2 );
    }
    
    @Override 
    public boolean collidesWith(Vector2 testPoint) {
        
        if ( testPoint.x < this.model.getPosition().x ) return false;
        if ( testPoint.x > this.model.getPosition().x + PlayerAvatarView.WIDTH ) return false;
        
        if ( testPoint.y < this.model.getPosition().y ) return false;
        if ( testPoint.y > this.model.getPosition().y + PlayerAvatarView.HEIGHT ) return false;
        
        return true;
    }
    
    @Override
    public Explosion createExplosion() {
        return new ExplosionView_Ship();
    }
}
