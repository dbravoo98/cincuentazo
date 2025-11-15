package com.example.cincuentazo.model;

import java.util.*;

/**
 * Main game model. Manages the deck, table, player and bot hands,
 * game rules, eliminations, and deck recycling when it runs out.
 * Note: adapt this file if your original project has separate Deck classes.
 */
public class GameModel {
    private final List<Card> deck = new ArrayList<>();
    private final List<Card> tableDeck = new ArrayList<>();
    private final List<Card> playerHand = new ArrayList<>();
    private final List<List<Card>> machinesHands = new ArrayList<>();
    private final List<Boolean> eliminated = new ArrayList<>();
    private int tablePoints = 0;
    private final int machinesCount;

    public GameModel(int machinesCount) {
        this.machinesCount = machinesCount;
        for (int i = 0; i < machinesCount; i++) eliminated.add(false);
        initDeck();
        shuffleDeck();
        initialDeal();
    }

    private void initDeck() {
        deck.clear();
        String[] suits = {"corazon","diamante","trebol","pica"};
        String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        for (String s : suits) {
            for (String v : values) {
                deck.add(new Card(v, s));
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(deck);
    }

    private Card drawFromDeckInternal() {
        if (deck.isEmpty()) refillDeckFromTable();
        if (deck.isEmpty()) return null;
        return deck.remove(0);
    }

    private void initialDeal() {
        playerHand.clear();
        for (int i = 0; i < 4; i++) {
            Card c = drawFromDeckInternal();
            if (c != null) playerHand.add(c);
        }
        machinesHands.clear();
        for (int m = 0; m < machinesCount; m++) {
            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Card c = drawFromDeckInternal();
                if (c != null) hand.add(c);
            }
            machinesHands.add(hand);
        }
        Card tableCard = drawFromDeckInternal();
        tableDeck.clear();
        if (tableCard != null) tableDeck.add(tableCard);
        updateTablePointsFromTop();
    }

    private void updateTablePointsFromTop() {
        int sum = 0;
        for (Card c : tableDeck) {
            String v = c.getValue().toUpperCase();
            if ("A".equals(v)) {
                if (sum + 10 <= 50) sum += 10; else sum += 1;
            } else if ("J".equals(v) || "Q".equals(v) || "K".equals(v)) sum -= 10;
            else if ("9".equals(v)) sum += 0;
            else {
                try { sum += Integer.parseInt(v); } catch (NumberFormatException e) {}
            }
        }
        tablePoints = sum;
    }

    /**
     * Jugador humano juega carta de índice handIndex.
     */
    public synchronized void playPlayerCard(int handIndex) throws NoPlayableCardException {
        if (handIndex < 0 || handIndex >= playerHand.size()) throw new IllegalArgumentException("índice inválido");
        Card card = playerHand.get(handIndex);
        int val = card.getValueForTable(tablePoints);
        if (val == Integer.MIN_VALUE) throw new NoPlayableCardException("Carta no jugable sin pasar de 50");
        playerHand.remove(handIndex);
        tableDeck.add(card);
        updateTablePointsFromTop();
        Card drawn = drawFromDeckInternal();
        if (drawn != null) playerHand.add(drawn);
    }

    /**
     * Machine plays its card.
     */
    public synchronized void playMachineCard(int machineId, int handIndex) {
        if (machineId < 0 || machineId >= machinesHands.size()) throw new IllegalArgumentException("machineId inválido");
        List<Card> hand = machinesHands.get(machineId);
        if (handIndex < 0 || handIndex >= hand.size()) return;
        Card card = hand.get(handIndex);
        int val = card.getValueForTable(tablePoints);
        if (val == Integer.MIN_VALUE) {
            return;
        }
        hand.remove(handIndex);
        tableDeck.add(card);
        updateTablePointsFromTop();
        Card drawn = drawFromDeckInternal();
        if (drawn != null) hand.add(drawn);
    }

    /**
     * Eliminates the machine and returns its cards to the bottom of the deck.
     */
    public synchronized void eliminatePlayer(int machineId) {
        if (machineId < 0 || machineId >= machinesHands.size()) return;
        if (eliminated.get(machineId)) return;
        List<Card> hand = machinesHands.get(machineId);
        if (!hand.isEmpty()) {
            deck.addAll(hand);
            hand.clear();
            Collections.shuffle(deck);
        }
        eliminated.set(machineId, true);
    }

    public synchronized boolean isEliminated(int machineId) {
        if (machineId < 0 || machineId >= eliminated.size()) return true;
        return eliminated.get(machineId);
    }

    public synchronized boolean isGameOver() {
        // El juego termina cuando no hay máquinas activas
        for (boolean e : eliminated) if (!e) return false;
        return true;
    }

    public synchronized int getTablePoints() { return tablePoints; }

    public synchronized List<Card> getMachineHand(int machineId) {
        if (machineId < 0 || machineId >= machinesHands.size()) return Collections.emptyList();
        return Collections.unmodifiableList(machinesHands.get(machineId));
    }

    public synchronized List<Card> getPlayerHand() {
        return Collections.unmodifiableList(playerHand);
    }

    public synchronized List<Card> getTableDeck() {
        return Collections.unmodifiableList(tableDeck);
    }

    public synchronized List<Card> getDeck() {
        return Collections.unmodifiableList(deck);
    }

    private void refillDeckFromTable() {
        if (tableDeck.size() <= 1) return;
        List<Card> toReturn = new ArrayList<>(tableDeck.subList(0, tableDeck.size() - 1));
        tableDeck.subList(0, tableDeck.size() - 1).clear();
        deck.addAll(toReturn);
        Collections.shuffle(deck);
    }

    // Helpers
    public synchronized int getDeckSize() { return deck.size(); }
    public synchronized int getTableDeckSize() { return tableDeck.size(); }
}
