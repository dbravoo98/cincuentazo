package com.example.cincuentazo.controller;

import com.example.cincuentazo.view.Alert.AlertBox;
import com.example.cincuentazo.view.DificultView;
import com.example.cincuentazo.view.HomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the home screen of the game, handling the navigation and displaying game instructions.
 *
 */
public class HomeController {

    @FXML
    private Button onPlay;

    @FXML
    private Button onExit;

    @FXML
    private Button onHow;

    @FXML
    private ImageView cincuentazoImage;

    private String Rules = """
                Objectivo:
                Ser el último jugador en pie evitando que la suma total de las cartas en la mesa supere los 50 puntos.
                
                Preparacion:
                - A cada jugador se le reparten 4 cartas.
                - Se coloca una carta inicial boca arriba en la mesa, comenzando la suma.
                - Las cartas restantes forman el mazo, colocado boca abajo.
                
                Reglas principales:
                - En tu turno, juega una carta de tu mano que no haga que la suma supere 50
                - Efectos de las cartas:
                  • 2–8 y 10: Suman su valor al total.
                  • 9: No afecta la suma.
                  • J, Q, K: Restan 10 a la suma.
                  • A: Suma 1 o 10, segun convenga mas.
                - Despues de jugar una carta, roba una del mazo para mantener 4 cartas en tu mano.
                
                Eliminacion:
                - Si no puedes jugar ninguna carta sin superar 50, quedas eliminado
                
                Fin del juego:
                - El juego termina cuando solo queda un jugador. Ese jugador es el ganador.
                """;

    /**
     * Initializes the home screen, setting up the images for buttons and title.
     */
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/cincuentazo/img/play.jpg"));
        ImageView imageView = new ImageView(image);
        onPlay.setGraphic(imageView);

        Image image2 = new Image(getClass().getResourceAsStream("/com/example/cincuentazo/img/howTo.jpg"));
        ImageView imageView2 = new ImageView(image2);
        onHow.setGraphic(imageView2);

        Image image3 = new Image(getClass().getResourceAsStream("/com/example/cincuentazo/img/exit.jpg"));
        ImageView imageView3 = new ImageView(image3);
        onExit.setGraphic(imageView3);

        Image image4 = new Image(getClass().getResourceAsStream("/com/example/cincuentazo/img/title.png"));
        cincuentazoImage.setImage(image4);
    }

    /**
     * Handles the exit button action, closing the home view.
     *
     * @param event The action event triggered by pressing the exit button.
     * @throws IOException If an error occurs while closing the view.
     */
    @FXML
    void onExitButton(ActionEvent event) throws IOException {
        HomeView.getInstance().close();
    }

    /**
     * Displays the game instructions in an alert box when the "How to play" button is pressed.
     *
     * @param event The action event triggered by pressing the "How to play" button.
     */
    @FXML
    void onHowButtton(ActionEvent event) {
        new AlertBox().InformationAlert("Cincuentazo", "HOW TO PLAY?", Rules);
    }

    /**
     * Navigates to the difficulty selection screen when the "Play" button is pressed.
     *
     * @param event The action event triggered by pressing the play button.
     * @throws IOException If an error occurs while navigating to the difficulty view.
     */
    @FXML
    void onPlatButton(ActionEvent event) throws IOException {
        HomeView.getInstance().close();
        DificultView.getInstance();
    }
}
