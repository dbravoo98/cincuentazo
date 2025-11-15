package com.example.cincuentazo.model;

import java.util.Random;

/**
 * Runnable that executes the logic of a bot:
 * - Waits between 2 and 4 seconds
 * - Chooses a card using a strategy
 * - Plays or gets eliminated
 */
public class BotPlayer implements Runnable {
    private final int id;
    private final GameModel model;
    private final BotStrategy strategy;
    private volatile boolean running = true;
    private final Random rnd = new Random();

    public BotPlayer(int id, GameModel model, BotStrategy strategy) {
        this.id = id;
        this.model = model;
        this.strategy = strategy;
    }

    public void stop() { running = false; }

    @Override
    public void run() {
        try {
            while (running && !model.isGameOver()) {
                int wait = 2000 + rnd.nextInt(2001); // 2000..4000 ms
                Thread.sleep(wait);

                synchronized (model) {
                    if (model.isGameOver() || model.isEliminated(id)) continue;
                    int chosen = strategy.chooseCardIndex(model, id);
                    if (chosen == -1) {
                        model.eliminatePlayer(id);
                    } else {
                        model.playMachineCard(id, chosen);
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
