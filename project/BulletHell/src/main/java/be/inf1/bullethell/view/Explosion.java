/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package be.inf1.bullethell.view;

import be.inf1.bullethell.model.Vector2;
import javafx.scene.layout.Region;

/**
 *
 * @author u0143348
 */
public abstract class Explosion extends Region {

    public abstract void setup(Vector2 position);
    public abstract Region getView();
    public abstract boolean update(); // returns false once it no longer has to be updated and can be safely removed
}
