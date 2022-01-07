/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.ImageController;
import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Enemy;
import be.inf1.bullethell.model.LoadedImage;
import be.inf1.bullethell.model.Player;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author u0143348
 */
public class EnemyView_Frigate extends SpaceshipView {
    private Enemy model;
    
    private static int WIDTH = 60;
    private static int HEIGHT = 80;
    
    private static int HALF_WIDTH = 30;
    private static int HALF_HEIGHT = 40;

    public EnemyView_Frigate(Enemy model) {
        super(model);
        this.model = model;
        this.setup();
    }

    public Enemy getModel() {
        return model;
    }
    
    @Override
    public void setup() {
        // re-enable this code to show the "hitbox"
//        Rectangle r = new Rectangle( 0, 0, EnemyView_Frigate.WIDTH, EnemyView_Frigate.HEIGHT);
//        r.setFill( Color.DARKGREY );
//        this.getChildren().add(r);

        Image image = ImageController.getImage(LoadedImage.Type.ENEMY_FRIGATE);
        ImageView iv = new ImageView( image );
        iv.setRotate(90); // the enemy images are facing DOWN by default, we want them facing LEFT 
        iv.setFitWidth( 93 ); // we don't use EnemyView_Frigate.WIDTH because we want the hitbox to be a bit smaller than the actual image
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        
        iv.setX( -30 ); // move a bit to the left visually so the hitbox matches the body of the dreadnought better
        
        this.getChildren().add(iv);
        
        this.update();
    }
    
    
    @Override
    public Vector2 getCenterPoint() {
        // offset x and y to get better visual leaving points of the bullets
        return new Vector2( this.model.getPosition().x + EnemyView_Frigate.WIDTH / 2 - 36, this.model.getPosition().y + EnemyView_Frigate.HEIGHT / 2 - 5 );
    }
    
    @Override 
    public boolean collidesWith(Vector2 testPoint) {
        if ( testPoint.x < this.model.getPosition().x ) return false;
        if ( testPoint.x > this.model.getPosition().x + EnemyView_Frigate.WIDTH ) return false;
        
        if ( testPoint.y < this.model.getPosition().y ) return false;
        if ( testPoint.y > this.model.getPosition().y + EnemyView_Frigate.HEIGHT ) return false;
        
        return true;
    }
    
    @Override
    public Explosion createExplosion() {
        return new ExplosionView_Ship();
    }
}
