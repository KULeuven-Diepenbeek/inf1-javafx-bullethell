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
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author u0143348
 */
public class GameViewController implements Initializable {

    // We don't want to store a list of every possible object here, that would be too unwieldy.
    // Instead, we store top-level objects from which we can retrieve other objects 
    // (for example, SpaceshipController gives us the main View and Model for the ships)
    private ArrayList<SpaceshipController> players;
    private ArrayList<PlayerStatsView> playerStats;
    private ArrayList<KeyboardInput> playerInputs;
    
    private ArrayList<SpaceshipController> enemies;
    
    private BulletController bulletController;
    private BulletGenerator laserBulletGenerator;
    private BulletGenerator rocketBulletGenerator;
    
    private long previousTime = 0;
    
    private boolean gameOver = false;
    
    private boolean DEBUG_stressTest = false;
    
    private BackgroundView background;
    
    @FXML
    private AnchorPane levelContainer;
    
    @FXML
    private VBox playerList;
    
    @FXML
    private Text fpsCounter;
    private long lastFPSupdate = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.players = new ArrayList<SpaceshipController>();
        this.enemies = new ArrayList<SpaceshipController>();
        this.playerStats = new ArrayList<PlayerStatsView>();
        this.playerInputs = new ArrayList<KeyboardInput>();
        
        this.bulletController = new BulletController( this.levelContainer );
        
        this.laserBulletGenerator = new BulletGenerator_Laser(this.bulletController);
        this.rocketBulletGenerator = new BulletGenerator_SinRocket(this.bulletController);
        
        ImageController.preloadImages();
        
        this.background = new BackgroundView();
        this.levelContainer.getChildren().add(this.background);
        
        KeyboardInput player1Input = new KeyboardInput( KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.SPACE );
        this.createPlayer("Player 1", Color.GREEN, new Vector2(100, 100), player1Input );
        
        KeyboardInput player2Input = new KeyboardInput( KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, KeyCode.CONTROL );
        this.createPlayer("Player 2", Color.BLUE, new Vector2(100, 300), player2Input );
        
        if ( this.DEBUG_stressTest ) {
            this.players.get(0).getShip().setHealth( 1000000 );
            this.players.get(1).getShip().setHealth( 1000000 );
        }
        
        this.createEnemies();
        
        this.levelContainer.setOnKeyPressed(this::keyPressed);
        this.levelContainer.setOnKeyReleased(this::keyReleased);
        this.levelContainer.setFocusTraversable(true);
        
        GameLoop gameLoop = new GameLoop(this);
        
        Timer t = new Timer();
        t.scheduleAtFixedRate( gameLoop, 0, GameLoop.DELTA_TIME );
        
        
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long deltaTime = now - previousTime;
                previousTime = now;
                
                if ( now - lastFPSupdate > 500000000 ) { // update each half second
                
                    // deltaTime is in nanoseconds, need to first get to ms, then we can get FPS
                    int FPS = Math.round(1000 / (deltaTime / 1000000));

                    fpsCounter.setText( "FPS: " + FPS );
                
                    lastFPSupdate = now;
                }
            };
        };
        at.start();
    }
    
    private void createPlayer(String name, Color color, Vector2 position, KeyboardInput input) {
        Player player = new Player( name, Player.MAX_HEALTH, color, position );
        player.setEnginePower( new Vector2(5,5) );
        PlayerAvatarView playerAvatar = new PlayerAvatarView(player);
        PlayerStatsView playerStats = new PlayerStatsView(player);
        
        playerAvatar.setup();
        this.levelContainer.getChildren().add( playerAvatar );
        
        playerStats.setup();
        this.playerList.getChildren().add( playerStats );
        
        SpaceshipController playerController = new SpaceshipController( player, playerAvatar, input, this.laserBulletGenerator );
        
        this.players.add( playerController );
        this.playerStats.add( playerStats );
        this.playerInputs.add( input );
    }
    
    private void createEnemies() {
        
        Enemy e1 = new Enemy( 5, new Vector2(900, 350)  );
        e1.setEnginePower( new Vector2(1,2) );
        AIInput_UpDown ei1 = new AIInput_UpDown();
        
        EnemyView_Frigate ev1 = new EnemyView_Frigate( e1 );
        ev1.setup();
        
        SpaceshipController sc1 = new SpaceshipController( e1, ev1, ei1, this.rocketBulletGenerator );
        
        this.levelContainer.getChildren().add( ev1 );
        this.enemies.add(sc1);
        
        
        Enemy e2 = new Enemy( 10, new Vector2(1100, 350)  );
        AIInput_Stationary ei2 = new AIInput_Stationary();
        
        EnemyView_Dreadnought ev2 = new EnemyView_Dreadnought( e2 );
        ev2.setup();
        
        SpaceshipController sc2 = new SpaceshipController( e2, ev2, ei2, this.laserBulletGenerator );
        
        this.levelContainer.getChildren().add( ev2 );
        this.enemies.add(sc2);
        
        if ( this.DEBUG_stressTest ) {
            for ( int i = 0; i < 100; ++i ) {
                Enemy e3 = new Enemy( 1, new Vector2(900 + (int) (Math.random() * 200), 10 + (int) (Math.random() * 700))  );
                e3.setEnginePower( new Vector2(1,2) );
                AIInput_UpDown ei3 = new AIInput_UpDown();

                EnemyView_Frigate ev3 = new EnemyView_Frigate( e3 );
                ev3.setup();

                SpaceshipController sc3 = new SpaceshipController( e3, ev3, ei3, this.rocketBulletGenerator );

                this.levelContainer.getChildren().add( ev3 );
                this.enemies.add(sc3);
            }
        }
    }
    
    // supposed to be called once per game loop tick (for example 30 times per second)
    public void update() {
        if ( this.gameOver ) {
            return;
        }
        
        
        // 1. process user input and move models
        this.updateModels();
        
        // 2. update corresponding views
        Platform.runLater( this::updateViews );
    }
    
    private void updateModels() {
        for ( SpaceshipController p : this.players ) {
            p.updateModels();
        }
        
        for ( SpaceshipController e : this.enemies ) {
            e.updateModels();
        }
        
        this.bulletController.updateModels();
    }
    
    private void updateViews() {
        this.background.update();
        
        for ( SpaceshipController p : this.players ) {
            p.updateViews();
        }
        
        for ( PlayerStatsView v : this.playerStats ) {
            v.update();
        }
        
        for ( SpaceshipController e : this.enemies ) {
            e.updateViews();
        }
        
        // calculate collisions between bullets and both player and enemy ships
        this.bulletController.calculateCollisions( this.players );
        this.bulletController.calculateCollisions( this.enemies );
        
        this.bulletController.updateViews();
        
        // bulletController has also updated ship health according to collisions that happened
        // we do have to handle destroyed ships here though
        for ( SpaceshipController p : this.players ) {
            if ( p.getShip().getHealth() <= 0 ) {
                // one of the players is dead... need to trigger game over view
                // TODO: do this with a timer so we don't immediately stop
                // TODO: show a proper game-over view!
                this.gameOver = true;
            }
        }
        
        ArrayList<SpaceshipController> enemiesToRemove = new ArrayList<SpaceshipController>();
        for ( SpaceshipController e : this.enemies ) {
            if ( e.getShip().getHealth() <= 0 ) {
                enemiesToRemove.add( e );
            }
        }
        for ( SpaceshipController e : enemiesToRemove ) {
            this.enemies.remove( e );
            this.levelContainer.getChildren().remove( e.getView() );
        }
        
        if ( this.gameOver ) {
            // TODO: handle this!
            System.out.println("Game over!");
        }
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
