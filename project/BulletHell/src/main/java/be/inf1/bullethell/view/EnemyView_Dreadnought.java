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
public class EnemyView_Dreadnought extends SpaceshipView {
    private Enemy model;
    
    private static int WIDTH = 100;
    private static int HEIGHT = 100;

    public EnemyView_Dreadnought(Enemy model) {
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
        Rectangle r = new Rectangle( 0, 0, EnemyView_Dreadnought.WIDTH, EnemyView_Dreadnought.HEIGHT);
        r.setFill( Color.DEEPPINK );
        this.getChildren().add(r);
        
        this.update();
    }
    
    @Override
    public Vector2 getCenterPoint() {
        return new Vector2( this.model.getPosition().x + EnemyView_Dreadnought.WIDTH / 2, this.model.getPosition().y + EnemyView_Dreadnought.HEIGHT / 2 );
    }
    
    @Override 
    public boolean collidesWith(Vector2 testPoint) {
        
        if ( testPoint.x < this.model.getPosition().x ) return false;
        if ( testPoint.x > this.model.getPosition().x + EnemyView_Dreadnought.WIDTH ) return false;
        
        if ( testPoint.y < this.model.getPosition().y ) return false;
        if ( testPoint.y > this.model.getPosition().y + EnemyView_Dreadnought.HEIGHT ) return false;
        
        return true;
    }
    
    @Override
    public Explosion createExplosion() {
        return new ExplosionView_Ship();
    }
}
