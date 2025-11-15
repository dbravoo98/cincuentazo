package com.example.cincuentazo.controller;

import com.example.cincuentazo.model.*;
import com.example.cincuentazo.view.Alert.AlertBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController {

    // --------------------------
    //  FXML
    // --------------------------
    @FXML private ImageView mesaCard;

    @FXML private ImageView card1;
    @FXML private ImageView card2;
    @FXML private ImageView card3;
    @FXML private ImageView card4;

    @FXML private Label lblPuntos;
    @FXML private Label lblTurno;
    @FXML private Button btnSalir;

    // Clicks de cartas
    @FXML private void onCard1Click() { onClickCard(0); }
    @FXML private void onCard2Click() { onClickCard(1); }
    @FXML private void onCard3Click() { onClickCard(2); }
    @FXML private void onCard4Click() { onClickCard(3); }

    // --------------------------
    //  Modelo y hilos
    // --------------------------
    private GameModel model;

    private final List<BotPlayer> bots = new ArrayList<>();
    private final List<Thread> botThreads = new ArrayList<>();

    private GameTimer timer;
    private Thread timerThread;

    private int machinesCount = 0;

    private boolean isHumanTurn = true;

    public void initialize() {

    }

    public void setGameModel(GameModel model, int machinesCount) {
        this.model = model;
        this.machinesCount = machinesCount;

        actualizarMesa();
        actualizarManoJugador();
        iniciarBots();
        iniciarTimer();

        lblTurno.setText("TU TURNO");
    }

    // --------------------------
    //  BOTS
    // --------------------------
    private void iniciarBots() {
        for (int i = 0; i < machinesCount; i++) {

            BotStrategy strat = (i % 2 == 0)
                    ? new SimpleStrategy()
                    : new AggressiveStrategy();

            BotPlayer bot = new BotPlayer(i, model, strat);
            Thread t = new Thread(bot, "Bot-" + i);
            t.setDaemon(true);
            t.start();

            bots.add(bot);
            botThreads.add(t);
        }
    }

    // --------------------------
    //  TIMER
    // --------------------------
    private void iniciarTimer() {
        timer = new GameTimer();
        timerThread = new Thread(timer, "GameTimer");
        timerThread.setDaemon(true);
        timerThread.start();
    }

    // --------------------------
    //  JUGADA HUMANA
    // --------------------------
    public void onClickCard(int index) {
        if (!isHumanTurn) return;

        try {
            model.playPlayerCard(index);
            isHumanTurn = false;

            actualizarMesa();
            actualizarManoJugador();
            revisarFinDelJuego();

            lblTurno.setText("JUGANDO MAQUINAS...");

            Platform.runLater(this::esperarTurnoBots);

        } catch (Exception e) {
            AlertBox.show("Movimiento invalido", "No puedes jugar esa carta, pasarias de 50.");
        }
    }

    // --------------------------
    //  TURNO DE BOTS
    // --------------------------
    private void esperarTurnoBots() {
        new Thread(() -> {
            try {
                Thread.sleep(500);

                Platform.runLater(() -> {
                    actualizarMesa();
                    actualizarManoJugador();
                    revisarFinDelJuego();

                    isHumanTurn = true;
                    lblTurno.setText("TU TURNO");
                });

            } catch (Exception ignored) {}
        }).start();
    }

    // --------------------------
    //  ACTUALIZAR MESA
    // --------------------------
    private void actualizarMesa() {
        if (model.getTableDeck().isEmpty()) return;

        Card cartaMesa = model.getTableDeck().get(model.getTableDeck().size() - 1);

        String path = "/com/example/cincuentazo/cards/" +
                cartaMesa.getValue() + "-" + cartaMesa.getSuit() + ".png";

        try {
            mesaCard.setImage(new Image(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream(path),
                            "No se encontró la imagen: " + path
                    )
            ));
        } catch (Exception e) {
            System.out.println("ERROR cargando imagen de mesa: " + path);
            e.printStackTrace();
        }

        lblPuntos.setText("Suma: " + model.getTablePoints());
    }

    // --------------------------
    //  ACTUALIZAR MANO
    // --------------------------
    private void actualizarManoJugador() {
        List<Card> mano = model.getPlayerHand();
        ImageView[] slots = {card1, card2, card3, card4};

        for (int i = 0; i < 4; i++) {
            if (i < mano.size()) {

                Card c = mano.get(i);
                String path = "/com/example/cincuentazo/cards/" +
                        c.getValue() + "-" + c.getSuit() + ".png";

                try {
                    slots[i].setImage(new Image(
                            Objects.requireNonNull(
                                    getClass().getResourceAsStream(path),
                                    "No se encontró la imagen: " + path
                            )
                    ));
                } catch (Exception e) {
                    System.out.println("ERROR cargando carta de jugador: " + path);
                }

            } else {
                slots[i].setImage(null);
            }
        }
    }

    // --------------------------
    //  FIN DEL JUEGO
    // --------------------------
    private void revisarFinDelJuego() {
        if (model.isGameOver()) {
            detenerHilos();
            AlertBox.show("FIN DEL JUEGO", "¡GANASTE! Todas las máquinas fueron eliminadas.");
            return;
        }

        if (model.getTablePoints() > 50) {
            detenerHilos();
            AlertBox.show("FIN DEL JUEGO", "Perdiste. Pasaste de 50.");
        }
    }

    // --------------------------
    //  SALIR
    // --------------------------
    @FXML
    private void salir() {
        detenerHilos();
        System.exit(0);
    }

    // --------------------------
    //  DETENER HILOS
    // --------------------------
    private void detenerHilos() {
        try {
            for (BotPlayer b : bots) b.stop();
            for (Thread t : botThreads) t.interrupt();

            if (timer != null) timer.stop();
            if (timerThread != null) timerThread.interrupt();

        } catch (Exception ignored) {}
    }
}
