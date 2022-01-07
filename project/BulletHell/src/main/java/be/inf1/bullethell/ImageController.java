package be.inf1.bullethell;

import be.inf1.bullethell.model.LoadedImage;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author u0143348
 */
public class ImageController {
    private static ArrayList<LoadedImage> images;

//    public ImageController() {
//    }
    
    public static void preloadImages() {
        ImageController.images = new ArrayList<LoadedImage>();
        
        ImageController.loadImage( LoadedImage.Type.PLAYER_BLUE, "player_blue.png" );
        ImageController.loadImage( LoadedImage.Type.PLAYER_GREEN, "player_green.png" );
        ImageController.loadImage( LoadedImage.Type.ENEMY_DREADNOUGHT, "enemy_dreadnought.png" );
        ImageController.loadImage( LoadedImage.Type.ENEMY_FRIGATE, "enemy_frigate.png" );
        ImageController.loadImage( LoadedImage.Type.BULLET_LASER, "bullet_laser.png" );
        ImageController.loadImage( LoadedImage.Type.BULLET_ROCKET, "bullet_rocket.png" );
        ImageController.loadImage( LoadedImage.Type.BACKGROUND_1, "background_1.png" );
        ImageController.loadImage( LoadedImage.Type.EXPLOSION_1, "explosion_1.gif" );
    }
    
    public static Image getImage( LoadedImage.Type type ) {
        for ( LoadedImage l : ImageController.images ) {
            if ( l.getType() == type ) {
                return l.getImage();
            }
        }
        
        return null;
    }
    
    private static void loadImage( LoadedImage.Type type, String fileName ) {
        
        // see also https://stackoverflow.com/questions/16099427/cannot-load-image-in-javafx
        Image image = new Image( "file:src/main/resources/be/inf1/bullethell/images/" +  fileName );
        
        LoadedImage loadedImage = new LoadedImage( type, fileName, image );
        
        ImageController.images.add( loadedImage );
    }
}
