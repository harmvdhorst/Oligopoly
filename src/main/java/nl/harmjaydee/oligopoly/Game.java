package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;

import java.util.Map;

public class Game extends DynamicScene {

    private Map<Integer, GamePlayer> players;
    private int turn = 0;
    private int totalStocks = 0;

    private String state;

    public Game() {

    }

    @Override
    public void setupScene() {
        setBackgroundImage("Background/dummy.png");
    }

    public void setupEntities() {
        var player1 = new GamePlayer(new Coordinate2D(0, 0));
        addEntity(player1);
    }

    public void nextTurn() {

    }

    public void startGame(int players) {

    }

    public void gameLoop() {

    }

    public void endGame(GamePlayer winner) {

    }
}
