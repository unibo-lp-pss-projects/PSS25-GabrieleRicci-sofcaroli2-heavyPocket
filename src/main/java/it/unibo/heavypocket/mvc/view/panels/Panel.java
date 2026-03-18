package it.unibo.heavypocket.mvc.view.panels;

import javafx.scene.layout.Region;

/**
 * Generic panel that exposes a JavaFX root node.
 */
public interface Panel {

    /**
     * Gets the root region to mount.
     * 
     * @return the panel root region
     */
    Region getRoot();
}
