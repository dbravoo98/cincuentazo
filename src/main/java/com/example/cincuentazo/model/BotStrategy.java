package com.example.cincuentazo.model;

/**
 * Strategy interface: defines the card selection policy for a bot.
 */
public interface BotStrategy {
    /**
     * Returns the index (0..handSize-1) of the card to play, or -1 if there is no valid card.
     */
    int chooseCardIndex(GameModel model, int botId);
}
