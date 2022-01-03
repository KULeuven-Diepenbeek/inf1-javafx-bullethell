package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Player;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author u0143348
 */
public class PlayerStatsView extends Region {
    private Text textRenderer;
    private Player model;
    
    public PlayerStatsView(Player model) {
        this.model = model;
    }
    
    public void setup () {
        this.textRenderer = new Text();
        this.getChildren().add( this.textRenderer );
        
        this.update();
    }
    
    // supposed to be called once per game loop tick
    public void update() {
        this.textRenderer.setFill( this.model.getColor() );
        this.textRenderer.setText( this.model.getName() + " : " + this.model.getHealth() + "/" + Player.MAX_HEALTH);
    }
    
    public Player getModel() {
        return this.model;
    }
}
