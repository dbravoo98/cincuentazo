package com.example.cincuentazo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * A class that creates and manages the home view window for the "Cincuentazo" game.
 * It uses the Singleton pattern to ensure only one instance of the window is created.
 */
public class HomeView extends Stage {

    /**
     * Constructor that initializes the Home view window.
     * It loads the FXML file, sets the scene, and displays the window.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public HomeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/example/cincuentazo/home-view.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        this.initStyle(StageStyle.UNDECORATED);
        this.setTitle("Cincuentazo");
        this.getIcons().add(new Image(this.getClass().getResourceAsStream("/com/example/cincuentazo/img/poker-table.png")));
        this.setScene(scene);
        this.show();
    }

    /**
     * Returns the single instance of the HomeView using the Singleton pattern.
     *
     * @return the HomeView instance.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public static HomeView getInstance() throws IOException {
        if (HomeView.HomeViewHolder.INSTANCE == null) {
            return HomeView.HomeViewHolder.INSTANCE = new HomeView();
        } else {
            return HomeView.HomeViewHolder.INSTANCE;
        }
    }

    /**
     * A private static class used to implement the Singleton pattern.
     */
    private static class HomeViewHolder {
        private static HomeView INSTANCE;
    }
}
