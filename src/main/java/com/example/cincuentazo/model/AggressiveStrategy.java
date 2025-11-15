package com.example.cincuentazo.model;

import java.util.List;

/**
 * Aggressive strategy: chooses the card that leaves the highest valid total (higher risk).
 */
public class AggressiveStrategy implements BotStrategy {
    @Override
    public int chooseCardIndex(GameModel model, int botId) {
        List<Card> hand = model.getMachineHand(botId);
        int puntos = model.getTablePoints();
        int bestIdx = -1;
        int bestSum = -1;
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            int val = c.getValueForTable(puntos);
            if (val == Integer.MIN_VALUE) continue;
            int newSum = puntos + val;
            if (newSum <= 50 && newSum > bestSum) {
                bestSum = newSum;
                bestIdx = i;
            }
        }
        return bestIdx;
    }
}
