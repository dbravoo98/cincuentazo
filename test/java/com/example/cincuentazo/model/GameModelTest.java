package com.example.cincuentazo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {

    GameModel game;

    @BeforeEach
    void setup() {
        game = new GameModel(2);  // dos máquinas
    }

    @Test
    void testInitialSetup() {
        assertNotNull(game.getPlayerHand());
        assertEquals(4, game.getPlayerHand().size());

        assertEquals(4, game.getMachineHand(0).size());
        assertEquals(4, game.getMachineHand(1).size());

        assertEquals(1, game.getTableDeckSize());  // una carta inicial
    }

    @Test
    void testPlayPlayerCard() throws Exception {
        int before = game.getTablePoints();

        game.playPlayerCard(0); // juega primera carta

        assertEquals(4, game.getPlayerHand().size()); // repone carta
        assertNotEquals(before, game.getTablePoints());
    }

    @Test
    void testPlayInvalidCardThrows() {
        // Creamos una carta imposible (nunca jugable)
        Card fake = new Card("999", "fake");

        // La insertamos en la mano manualmente
        game.getPlayerHand().clear();
        game.getPlayerHand().add(fake);

        assertThrows(NoPlayableCardException.class,
                () -> game.playPlayerCard(0));
    }

    @Test
    void testEliminatePlayer() {
        assertFalse(game.isEliminated(0));

        game.eliminatePlayer(0);

        assertTrue(game.isEliminated(0));
    }

    @Test
    void testGameOver() {
        assertFalse(game.isGameOver());

        game.eliminatePlayer(0);
        game.eliminatePlayer(1);

        assertTrue(game.isGameOver());
    }
}
