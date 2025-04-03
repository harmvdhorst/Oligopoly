package nl.harmjaydee.oligopoly.screen;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.StaticScene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import nl.harmjaydee.oligopoly.Oligopoly;
import nl.harmjaydee.oligopoly.utils.Button;

public class MainScreen extends DynamicScene {
    private final Oligopoly oligopoly;
    private final int maxPlayers = 4;
    private final int minPlayers = 2;

    private int selectedPlayers = 2;
    private int marginBottom = 100;

    private TextEntity text;

    public MainScreen(Oligopoly oligopoly) {
        this.oligopoly = oligopoly;
    }

    @Override
    public void setupScene() {

    }

    @Override
    public void setupEntities () {
        var oligopolyText = new TextEntity(
                new Coordinate2D(getWidth() / 2, getHeight() / 4),
                "oligopoly");
        oligopolyText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        oligopolyText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 80));
        addEntity(oligopolyText);

        Button startbutton = new Button(new Coordinate2D(getWidth() / 2 + 50, getHeight() - marginBottom), new Size(100, 50), Color.BLACK, Color.WHITE, Color.BLACK, "Start", () -> {
            oligopoly.getGame().startGame(selectedPlayers);
            oligopoly.setActiveScene(1);
        });

        Button addPlayers = new Button(new Coordinate2D(getWidth()/2 - 150, getHeight()/2), new Size(50, 50), Color.BLACK, Color.WHITE, Color.BLACK, "+", () -> {
            if(selectedPlayers == maxPlayers) return;
            selectedPlayers++;
            text.setText("" + selectedPlayers);
        });

        addEntity(addPlayers);

        Button removePlayers = new Button(new Coordinate2D(getWidth()/2 + 150, getHeight()/2), new Size(50, 50), Color.BLACK, Color.WHITE, Color.BLACK, "-", () -> {
            if(selectedPlayers == minPlayers) return;
            selectedPlayers--;
            text.setText(selectedPlayers + "");
        });

        addEntity(removePlayers);

        text = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 2), "" + selectedPlayers);
        text.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        text.setFont(Font.font("Roboto", 20));

        addEntity(text);

        startbutton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(startbutton);



    }
}
