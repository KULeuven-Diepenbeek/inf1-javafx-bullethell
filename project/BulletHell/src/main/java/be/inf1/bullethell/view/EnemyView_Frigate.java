/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Enemy;
import be.inf1.bullethell.model.Player;
import be.inf1.bullethell.model.Vector2;
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
    
    private static int WIDTH = 40;
    private static int HEIGHT = 40;
    
    private static int HALF_WIDTH = 20;
    private static int HALF_HEIGHT = 20;

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
        // TODO: this is just a placeholder, want to replace with an actual image later on
        Circle c = new Circle(0, 0, HEIGHT / 2);
        c.setFill( Color.DARKGRAY );
        this.getChildren().add(c);
        
        this.update();
    }
    
    
    @Override
    public Vector2 getCenterPoint() {
        // circle centerpoint is already the normal position, no offsetting from top left needed!
        return new Vector2( this.model.getPosition().x, this.model.getPosition().y );
    }
    
    @Override 
    public boolean collidesWith(Vector2 testPoint) {
        
        // circle centerpoint is the position, so we need to check in a radius around it!
        if ( testPoint.x < this.model.getPosition().x - EnemyView_Frigate.HALF_WIDTH ) return false;
        if ( testPoint.x > this.model.getPosition().x + EnemyView_Frigate.HALF_WIDTH ) return false;
        
        if ( testPoint.y < this.model.getPosition().y - EnemyView_Frigate.HALF_HEIGHT ) return false;
        if ( testPoint.y > this.model.getPosition().y + EnemyView_Frigate.HALF_HEIGHT ) return false;
        
        return true;
    }
    
    @Override
    public Explosion createExplosion() {
        return new ExplosionView_Ship();
    }
}
