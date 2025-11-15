package com.example.cincuentazo.model;

/**
 * Simple facade that wraps operations exposed by GameModel.
 * It simplifies interaction from controllers/views.
 */
public class GameFacade {
    private final GameModel model;

    public GameFacade(GameModel model) {
        this.model = model;
    }

    public int getTablePoints() { return model.getTablePoints(); }
    public void playPlayerCard(int idx) throws NoPlayableCardException { model.playPlayerCard(idx); }
    public void playMachineCard(int id, int idx) { model.playMachineCard(id, idx); }
    public boolean isGameOver() { return model.isGameOver(); }
}
