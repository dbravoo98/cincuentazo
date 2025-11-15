package com.example.cincuentazo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of cards in the game.
 * It allows for initializing, shuffling, dealing cards, and adding new cards to the deck.
 *
 */
public class Deck {
    /**
     * List that contains the cards in the deck.
     */
    private List<Card> cards;

    /**
     * Constructor that initializes the deck of cards.
     * It calls the {@link #initializeDeck()} method to fill the deck with cards
     * and then shuffles them using {@link #shuffle()}.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    /**
     * Initializes the deck of cards by adding all possible cards
     * with the defined values and suits.
     */
    private void initializeDeck() {
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a"};
        String[] suits = {"corazon", "diamante", "trebol", "pica"};
        for (String suit : suits) {
            for (String value : values) {
                cards.add(new Card(value, suit));
            }
        }
    }

    /**
     * Shuffles the cards in the deck randomly.
     * Uses {@link Collections#shuffle(List)} to shuffle the cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Deals a card from the deck.
     * @return The dealt card, or {@code null} if the deck is empty.
     */
    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }

    /**
     * Adds a set of new cards to the deck and shuffles it.
     * @param newCards The list of cards to add.
     */
    public void addCards(List<Card> newCards) {
        cards.addAll(newCards);
        shuffle();
    }

    /**
     * Gets the number of remaining cards in the deck.
     * @return The number of remaining cards in the deck.
     */
    public int remainingCards() {
        return cards.size();
    }

    /**
     * Gets the list of all the cards in the deck.
     * @return The list of cards in the deck.
     */
    public List<Card> getCards() {
        return cards;
    }
}
