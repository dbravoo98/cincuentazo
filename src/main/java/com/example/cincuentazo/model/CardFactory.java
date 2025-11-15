package com.example.cincuentazo.model;

/**
 * Simple factory for creating cards.
 */
public class CardFactory {
    public static Card create(String value, String suit) {
        return new Card(value, suit);
    }
}
