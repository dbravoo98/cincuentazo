package com.example.cincuentazo.model;

import javafx.scene.image.Image;

/**
 * This class represents a card in the game.
 * It stores the card's value (e.g., "A", "J", "K"), suit (e.g., "hearts"), and an image reference.
 * También proporciona helpers para el valor que debe usarse en la mesa (A -> 1 o 10).
 */
public class Card {
    private String valor;
    private String palo;
    private int puntaje;
    private Image image; // si tu proyecto usa Image; en caso contrario puedes eliminar

    public Card(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public String getValue() {
        return valor;
    }

    public String getSuit() {
        return palo;
    }

    /**
     * Devuelve el valor "por defecto":
     * A -> 1, J/Q/K -> -10, 9 -> 0, números -> su valor.
     */
    public int getNumericValue() {
        String v = valor.toUpperCase();
        if ("A".equals(v)) return 1;
        if ("J".equals(v) || "Q".equals(v) || "K".equals(v)) return -10;
        if ("9".equals(v)) return 0;
        try { return Integer.parseInt(v); } catch (NumberFormatException ex) { return 0; }
    }

    /**
     * Dado el estado actual de la mesa, devuelve el valor que esta carta aportaría.
     * Para 'A' prueba 10 y 1, devolviendo la mayor que no haga pasar de 50.
     * Retorna Integer.MIN_VALUE si la carta no es jugable (p.ej. número que pasaría de 50).
     */
    public int getValueForTable(int currentPoints) {
        String v = valor.toUpperCase();
        if ("A".equals(v)) {
            if (currentPoints + 10 <= 50) return 10;
            if (currentPoints + 1 <= 50) return 1;
            return Integer.MIN_VALUE;
        }
        if ("J".equals(v) || "Q".equals(v) || "K".equals(v)) {
            return -10;
        }
        if ("9".equals(v)) return 0;
        try {
            int num = Integer.parseInt(v);
            if (currentPoints + num <= 50) return num;
            return Integer.MIN_VALUE;
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}
