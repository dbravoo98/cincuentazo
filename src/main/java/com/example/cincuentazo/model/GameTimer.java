package com.example.cincuentazo.model;

/**
 * Game seconds counter thread.
 */
public class GameTimer implements Runnable {
    private volatile boolean running = true;
    private int seconds = 0;

    public void stop() { running = false; }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                seconds++;
                // si tienes listeners, notifícalos aquí (Platform.runLater para UI)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public int getSeconds() { return seconds; }
}
