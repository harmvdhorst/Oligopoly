package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private final Map<Integer, GamePlayer> players = new HashMap<>();
    private int turn = 0;
    private int totalStocks = 0;

    private int bankBalance = 0;

    private String state;

    public Game() {

    }

    public void nextTurn() {
        this.turn++;
        if(turn > players.size()) {
            turn = 0;
        }
    }

    public void startGame(int players) {
        for (int i = 0; i < players; i++) {
            this.players.put(i, new GamePlayer(this,i, "", new Coordinate2D()));
        }
    }

    public void gameLoop() {

    }

    public void endGame(GamePlayer winner) {

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
