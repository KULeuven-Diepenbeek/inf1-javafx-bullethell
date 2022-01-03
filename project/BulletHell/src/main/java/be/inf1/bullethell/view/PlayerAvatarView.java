/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Player;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author u0143348
 */
public class PlayerAvatarView extends Region {
    private Player model;
    
    private static int WIDTH = 100;
    private static int HEIGHT = 30;

    public PlayerAvatarView(Player model) {
        this.model = model;
        this.setup();
    }

    public Player getModel() {
        return model;
    }
    
    public void setup() {
        // TODO: this is just a placeholder, want to replace with an actual image later on
        Rectangle r = new Rectangle( 0, 0, PlayerAvatarView.WIDTH, PlayerAvatarView.HEIGHT);
        r.setFill( this.model.getColor() );
        this.getChildren().add(r);
        
        this.update();
    }
    
    // supposed to be called once per game loop tick
    public void update() {
        this.setTranslateX( this.model.getPosition().x );
        this.setTranslateY( this.model.getPosition().y );
    }
}
