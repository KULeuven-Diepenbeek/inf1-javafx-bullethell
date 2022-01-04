/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Enemy;
import be.inf1.bullethell.model.Player;
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
        c.setFill( Color.BLACK );
        this.getChildren().add(c);
        
        this.update();
    }
}
