package com.example.cincuentazo.controller;

import com.example.cincuentazo.model.GameModel;
import com.example.cincuentazo.view.DificultView;
import com.example.cincuentazo.view.GameView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DificultController {

    @FXML private Button btnFacil;
    @FXML private Button btnMedio;
    @FXML private Button btnDificil;

    private int machinesCount = 0;

    @FXML
    public void initialize() {
        // nada por ahora
    }

    @FXML
    private void onFacil() {
        machinesCount = 1;
        iniciarPartida();
    }

    @FXML
    private void onMedio() {
        machinesCount = 2;
        iniciarPartida();
    }

    @FXML
    private void onDificil() {
        machinesCount = 3;
        iniciarPartida();
    }

    private void iniciarPartida() {
        try {
            // Crear modelo con cantidad de máquinas
            GameModel gameModel = new GameModel(machinesCount);

            // Mostrar vista del juego
            GameView gameView = GameView.getInstance();
            gameView.show();

            // Obtener el controlador CORRECTO
            GameController controller = gameView.getGameController();

            // Pasar modelo y número de máquinas
            controller.setGameModel(gameModel, machinesCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
