package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.YaegerGame;
import nl.harmjaydee.oligopoly.screen.GameScreen;

public class Oligopoly extends YaegerGame {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {

    }

    @Override
    public void setupScenes() {
        addScene(0, new GameScreen());
    }

}
