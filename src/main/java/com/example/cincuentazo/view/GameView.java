package com.example.cincuentazo.view;

import com.example.cincuentazo.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * A class that creates and manages the main game view window for the "Cincuentazo" game.
 * It uses the Singleton pattern to ensure only one instance of the window is created.
 */
public class GameView extends Stage {

    private GameController gameController;

    /**
     * Constructor that initializes the Game view window.
     * It loads the FXML file, sets the scene, and displays the window.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/example/cincuentazo/game-view.fxml"));
        Parent root = (Parent) loader.load();
        this.gameController = (GameController) loader.getController();
        Scene scene = new Scene(root);
        this.initStyle(StageStyle.UNDECORATED);
        this.setTitle("Cincuentazo");
        this.getIcons().add(new Image(this.getClass().getResourceAsStream("/com/example/cincuentazo/img/poker-table.png")));
        this.setScene(scene);
        this.show();
    }

    /**
     * Gets the GameController associated with this GameView.
     *
     * @return the GameController instance.
     */
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * Returns the single instance of the GameView using the Singleton pattern.
     *
     * @return the GameView instance.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public static GameView getInstance() throws IOException {
        if (GameView.GameViewHolder.INSTANCE == null) {
            return GameView.GameViewHolder.INSTANCE = new GameView();
        } else {
            return GameView.GameViewHolder.INSTANCE;
        }
    }

    /**
     * A private static class used to implement the Singleton pattern.
     */
    private static class GameViewHolder {
        private static GameView INSTANCE;
    }
}
