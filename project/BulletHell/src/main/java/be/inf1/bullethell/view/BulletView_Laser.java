/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Collidable;
import be.inf1.bullethell.model.Collision;
import be.inf1.bullethell.model.Enemy;
import be.inf1.bullethell.model.Player;
import be.inf1.bullethell.model.Vector2;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author u0143348
 */
public class BulletView_Laser extends BulletView {
    private static int WIDTH = 50;
    private static int HEIGHT = 10;
    
    private static int HALF_WIDTH = WIDTH / 2;
    private static int HALF_HEIGHT = HEIGHT / 2;

    public BulletView_Laser(Bullet model) {
        super(model);
    }
    
    public void setup() {
        // TODO: this is just a placeholder, want to replace with an actual image later on
        Rectangle l = new Rectangle( 0, 0, BulletView_Laser.WIDTH, BulletView_Laser.HEIGHT);
        l.setFill( Color.CYAN );
        this.getChildren().add(l);
        
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
