package com.example.cincuentazo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card("5", "corazon");

        assertEquals("5", card.getValue());
        assertEquals("corazon", card.getSuit());
    }

    @Test
    void testNumericValue() {
        assertEquals(1, new Card("A", "pica").getNumericValue());
        assertEquals(-10, new Card("K", "trebol").getNumericValue());
        assertEquals(0, new Card("9", "diamante").getNumericValue());
        assertEquals(7, new Card("7", "corazon").getNumericValue());
    }

    @Test
    void testValueForTable() {
        Card ace = new Card("A", "pica");
        assertEquals(10, ace.getValueForTable(30)); // puede valer 10
        assertEquals(1, ace.getValueForTable(45));  // solo cabe 1
        assertEquals(Integer.MIN_VALUE, ace.getValueForTable(50)); // no cabe

        Card k = new Card("K", "corazon");
        assertEquals(-10, k.getValueForTable(20));

        Card seven = new Card("7", "diamante");
        assertEquals(7, seven.getValueForTable(40));
        assertEquals(Integer.MIN_VALUE, seven.getValueForTable(49)); // pasaría de 50
    }

    @Test
    void testToString() {
        Card card = new Card("3", "diamante");
        assertEquals("3 de diamante", card.toString());
    }
}
