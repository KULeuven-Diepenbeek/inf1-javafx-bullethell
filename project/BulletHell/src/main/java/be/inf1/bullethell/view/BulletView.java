/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Bullet;
import be.inf1.bullethell.model.Enemy;
import be.inf1.bullethell.model.Player;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author u0143348
 */
public class BulletView extends Region {
    private Bullet model;
    
    private static int WIDTH = 100;
    private static int HEIGHT = 30;

    public BulletView(Bullet model) {
        this.model = model;
        this.setup();
    }

    public Bullet getModel() {
        return model;
    }
    
    public void setup() {
        // TODO: this is just a placeholder, want to replace with an actual image later on
        Polygon p = new Polygon();
        // going left
        if ( this.model.getVelocity().x <= 0 ) {
            p.getPoints().addAll(new Double[]{
                0.0, 10.0,
                20.0, 0.0,
                20.0, 20.0 });
        }
        // going right
        else {
            p.getPoints().addAll(new Double[]{
                0.0, 0.0,
                20.0, 10.0,
                0.0, 20.0 });
        }
        
        p.setFill( Color.PURPLE );
        this.getChildren().add(p);
        
        this.update();
    }
    
    // supposed to be called once per game loop tick
    public void update() {
        this.setTranslateX( this.model.getPosition().x );
        this.setTranslateY( this.model.getPosition().y );
    }
}
