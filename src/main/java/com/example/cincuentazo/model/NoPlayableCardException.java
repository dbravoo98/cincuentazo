package com.example.cincuentazo.model;

/**
 * Exception thrown when a player has no playable card.
 */
public class NoPlayableCardException extends Exception {
    public NoPlayableCardException(String message) {
        super(message);
    }
}