/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package be.inf1.bullethell;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import be.inf1.bullethell.model.*;
import be.inf1.bullethell.view.*;
import java.util.Timer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author u0143348
 */
public class GameViewController implements Initializable {

    // we will keep a list of just the views, since we can access the models from there
    private ArrayList<PlayerAvatarView> playerAvatars;
    private ArrayList<PlayerStatsView> playerStats;
    private ArrayList<KeyboardInput> playerInputs;
    
    @FXML
    private AnchorPane levelContainer;
    
    @FXML
    private VBox playerList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.playerAvatars = new ArrayList<PlayerAvatarView>();
        this.playerStats = new ArrayList<PlayerStatsView>();
        this.playerInputs = new ArrayList<KeyboardInput>();
        
        KeyboardInput player1Input = new KeyboardInput( KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.SPACE );
        this.createPlayer("Player 1", Color.RED, new Vector2(100, 100), player1Input );
        
        KeyboardInput player2Input = new KeyboardInput( KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.CONTROL );
        this.createPlayer("Player 2", Color.BLUE, new Vector2(100, 300), player2Input );
        
        this.levelContainer.setOnKeyPressed(this::keyPressed);
        this.levelContainer.setOnKeyReleased(this::keyReleased);
        this.levelContainer.setFocusTraversable(true);
        
        GameLoop gameLoop = new GameLoop(this);
        
        Timer t = new Timer();
        t.scheduleAtFixedRate( gameLoop, 0, GameLoop.DELTA_TIME );
    }    
    
    // supposed to be called once per game loop tick (for example 30 times per second)
    public void update() {
        // 1. process user input and move models
        this.updateModels();
        
        // 2. update corresponding views
        Platform.runLater( this::updateViews );
    }
    
    private void updateModels() {
        for ( int i = 0; i < this.playerInputs.size(); ++i ) {
            KeyboardInput input = this.playerInputs.get(i);
            Player player = this.playerAvatars.get(i).getModel();
            
            Vector2 velocity = player.getVelocity(); // change the velocity directly
            // reset velocity to make input really snappy! 
            // if player stops pressing button, movement in that directly immediately stops (no momentum for now)
            velocity.x = 0;
            velocity.y = 0;
            
            if ( input.up() ) {
                velocity.y = -1;
            }
            else if ( input.down() ) {
                velocity.y = 1;
            }
            
            if ( input.left() ) {
                velocity.x = -1;
            }
            else if ( input.right() ) {
                velocity.x = 1;
            }
            
            player.update();
        }
    }
    
    private void updateViews() {
        for ( PlayerAvatarView v : this.playerAvatars ) {
            v.update();
        }
        
        for ( PlayerStatsView v : this.playerStats ) {
            v.update();
        }
    }
    
    private void createPlayer(String name, Color color, Vector2 position, KeyboardInput input) {
        Player player = new Player( name, Player.MAX_HEALTH, color, position );
        PlayerAvatarView playerAvatar = new PlayerAvatarView(player);
        PlayerStatsView playerStats = new PlayerStatsView(player);
        
        playerAvatar.setup();
        this.levelContainer.getChildren().add( playerAvatar );
        
        playerStats.setup();
        this.playerList.getChildren().add( playerStats );
        
        this.playerAvatars.add( playerAvatar );
        this.playerStats.add( playerStats );
        this.playerInputs.add( input );
    }
    
    private void keyPressed( KeyEvent evt ) {
        for ( KeyboardInput handler : this.playerInputs ) {
            handler.handleKeyPress( evt );
        }
    }
    
    private void keyReleased( KeyEvent evt ) {
        for ( KeyboardInput handler : this.playerInputs ) {
            handler.handleKeyRelease( evt );
        }
    }
}
