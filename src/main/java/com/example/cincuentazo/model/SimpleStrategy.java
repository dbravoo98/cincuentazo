package com.example.cincuentazo.model;

import java.util.List;

/**
 * Simple strategy: chooses the first card that can be played without exceeding 50.
 */
public class SimpleStrategy implements BotStrategy {
    @Override
    public int chooseCardIndex(GameModel model, int botId) {
        List<Card> hand = model.getMachineHand(botId);
        int puntos = model.getTablePoints();
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            int val = c.getValueForTable(puntos);
            if (val == Integer.MIN_VALUE) continue;
            if (puntos + val <= 50) return i;
        }
        return -1;
    }
}
