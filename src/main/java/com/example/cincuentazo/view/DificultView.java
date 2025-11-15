package com.example.cincuentazo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class that creates and manages the Difficulty view window for the game.
 * It uses the Singleton pattern to ensure only one instance is created.
 *
 */
public class DificultView extends Stage {

    /**
     * Constructor that initializes the Difficulty view window.
     * It loads the FXML file, sets the scene, and displays the window.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public DificultView() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/example/cincuentazo/Dificultad.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        this.setTitle("Cincuentazo");
        this.getIcons().add(new Image(this.getClass().getResourceAsStream("/com/example/cincuentazo/img/poker-table.png")));
        this.setScene(scene);
        this.show();
    }

    /**
     * Returns the single instance of the DificultView using the Singleton pattern.
     *
     * @return the DificultView instance.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public static DificultView getInstance() throws IOException {
        if (DificultViewHolder.INSTANCE == null) {
            return DificultView.DificultViewHolder.INSTANCE = new DificultView();
        } else {
            return DificultViewHolder.INSTANCE;
        }
    }

    /**
     * A private static class used to implement the Singleton pattern.
     */
    private static class DificultViewHolder {
        private static DificultView INSTANCE;
    }
}
