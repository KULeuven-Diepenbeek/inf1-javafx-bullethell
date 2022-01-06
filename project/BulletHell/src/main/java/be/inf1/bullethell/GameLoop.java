package be.inf1.bullethell;

import java.util.TimerTask;

/**
 *
 * @author u0143348
 */
public class GameLoop extends TimerTask {

    private GameViewController controller;
    
    public static int DELTA_TIME = 33; // time in ms between ticks. 33 for ~30FPS, 16 for ~60FPS 
    public static int FRAMES_PER_SECOND = 30;
    
    public GameLoop(GameViewController controller) {
        this.controller = controller;
    }
    
    @Override
    public void run() {
        this.controller.update();
    }
}
