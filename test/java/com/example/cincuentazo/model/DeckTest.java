package com.example.cincuentazo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    void testDeckInitialization() {
        Deck deck = new Deck();
        assertEquals(52, deck.remainingCards());  // 13 valores × 4 palos
    }

    @Test
    void testDealCard() {
        Deck deck = new Deck();
        int before = deck.remainingCards();

        Card c = deck.dealCard();

        assertNotNull(c);
        assertEquals(before - 1, deck.remainingCards());
    }

    @Test
    void testShuffleChangesOrder() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();

        deck2.shuffle();

        assertNotEquals(deck1.getCards(), deck2.getCards());
    }

    @Test
    void testAddCards() {
        Deck deck = new Deck();
        int before = deck.remainingCards();

        deck.addCards(java.util.List.of(new Card("A", "pica")));

        assertEquals(before + 1, deck.remainingCards());
    }
}
