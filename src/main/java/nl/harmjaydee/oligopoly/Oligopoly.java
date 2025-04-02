package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import nl.harmjaydee.oligopoly.screen.GameScreen;

public class Oligopoly extends YaegerGame {

    private Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        setSize(new Size(860, 860));

        game = new Game();
    }

    @Override
    public void setupScenes() {
        addScene(0, new GameScreen(game));
    }

}
