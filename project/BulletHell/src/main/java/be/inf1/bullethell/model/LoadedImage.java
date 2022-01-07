/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.bullethell.model;

import javafx.scene.image.Image;

/**
 *
 * @author u0143348
 */
public class LoadedImage {
    
    public enum Type {
        PLAYER_GREEN,
        PLAYER_BLUE,
        
        ENEMY_FRIGATE,
        ENEMY_DREADNOUGHT,
        
        BULLET_LASER,
        BULLET_ROCKET,
        
        EXPLOSION_1,
        
        BACKGROUND_1
    }
    
    private Type type; 
    private String filePath;
    private Image image;

    public LoadedImage(Type type, String filePath, Image image) {
        this.type = type;
        this.filePath = filePath;
        this.image = image;
    }

    public Type getType() {
        return type;
    }

    public String getFilePath() {
        return filePath;
    }

    public Image getImage() {
        return image;
    }
}
