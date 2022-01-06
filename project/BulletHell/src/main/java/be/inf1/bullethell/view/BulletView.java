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
public abstract class BulletView extends Region implements Collidable {
    protected Bullet model;

    public BulletView(Bullet model) {
        this.model = model;
        this.setup();
    }

    public Bullet getModel() {
        return model;
    }
    
    public abstract void setup();
    
    // supposed to be called once per game loop tick
    public void update() {
        this.setTranslateX( this.model.getPosition().x );
        this.setTranslateY( this.model.getPosition().y );
    }
    
    protected abstract int getBulletWidth();
    protected abstract int getBulletHeight();
    
    @Override
    public boolean collidesWith(Collidable c) {
        Vector2 testPoint = c.getCenterPoint();
        
        return this.collidesWith( testPoint );
    }
    
    @Override 
    public boolean collidesWith(Vector2 testPoint) {
        
        if ( testPoint.x < this.model.getPosition().x ) return false;
        if ( testPoint.x > this.model.getPosition().x + this.getBulletWidth() ) return false;
        
        if ( testPoint.y < this.model.getPosition().y ) return false;
        if ( testPoint.y > this.model.getPosition().y + this.getBulletHeight() ) return false;
        
        return true;
    }
    
    @Override
    public Explosion createExplosion() {
        return new ExplosionView_Bullet();
    }
}
