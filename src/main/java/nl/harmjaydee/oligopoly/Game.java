package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.Coordinate2D;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private final Map<Integer, GamePlayer> players = new HashMap<>();
    private int turn = 0;
    private int totalStocks = 0;

    private int bankBalance = 0;

    private int lastThrown = 0;

    public Game() {

    }

    public void nextTurn() {
        lastThrown = 0;
        this.turn++;
        if(turn == players.size()) {
            turn = 0;
        }
    }

    public void startGame(int players) {
        for (int i = 0; i < players; i++) {
            this.players.put(i, new GamePlayer(this,i, "sprites/player" + i + ".png", new Coordinate2D(80, 710)));
        }
    }

    public void endGame(GamePlayer winner) {

    }

    public void setLastThrown(int lastThrown) {
        this.lastThrown = lastThrown;
    }

    public void removeBankBalance(int amount) {
        bankBalance -= amount;
    }

    public void addBankBalance(int amount) {
        bankBalance += amount;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public GamePlayer getPlayer(int playerId) {
        return this.players.get(playerId);
    }

    public GamePlayer getCurrentPlayer() {
        return this.players.get(this.turn);

    }

    public Map<Integer, GamePlayer> getPlayers() {
        return players;
    }

}
