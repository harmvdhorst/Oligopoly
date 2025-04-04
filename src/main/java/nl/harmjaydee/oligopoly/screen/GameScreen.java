package nl.harmjaydee.oligopoly.screen;

import com.github.hanyaeger.api.*;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.harmjaydee.oligopoly.Game;
import nl.harmjaydee.oligopoly.menu.BuyStocksMenu;
import nl.harmjaydee.oligopoly.menu.DobbelMenu;
import nl.harmjaydee.oligopoly.menu.InfoMenu;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.SystemTile;
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.Button;
import nl.harmjaydee.oligopoly.utils.RectangleWrapper;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends DynamicScene {

    private final Game game;
    private final Map<Integer, Tile> tiles = new HashMap<>();
    private Button dobbelButton;

    public GameScreen(Game game) {
        this.game = game;
    }


    @Override
    public void setupScene() {

    }

    @Override
    public void setupEntities() {
        addEntity(new RectangleWrapper(new Coordinate2D(50 - 1, 50 - 1), new Size(760 + 2, 760 + 2), Color.BLACK));
        for (Tiles tile : Tiles.values()) {
            if(tile.getWorth() == 0){
                addTile(new SystemTile(this, tile));
            } else {
                addTile(new PlayerTile(this, tile));
            }
        }
        addEntity(new RectangleWrapper(new Coordinate2D(50 + 110 + 1, 50 + 110 + 1), new Size(540 - 2, 540 - 2), Color.WHITE));

        TextEntity text = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 2), "Oligopoly");
        text.setFont(Font.font("Roboto", 30));
        text.setStrokeColor(Color.BLACK);
        text.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(text);

        System.out.println("Loaded " + tiles.size() + " tiles");

        Button button = new Button(new Coordinate2D(getWidth() / 2 - 100, 50 + 110 + (7 * 60)), new Size(100, 30), Color.BLACK, Color.WHITE, Color.BLACK, "Eigendommen", () -> {
            InfoMenu infoMenu = new InfoMenu(game.getCurrentPlayer(), this);
            addEntity(infoMenu);
        });

        dobbelButton = new Button(new Coordinate2D(getWidth() / 2 + 100, 50 + 110 + (7 * 60)), new Size(100, 30), Color.BLACK, Color.WHITE, Color.BLACK, "Dobbelen", () -> {
            addEntity(new DobbelMenu(this, (thrown) -> {
                game.getCurrentPlayer().addGoal(thrown);
                game.getCurrentPlayer().setOnRightTile(false);
            }));
        });

        addEntity(button);
        addEntity(dobbelButton);

        game.getPlayers().values().forEach(this::addEntity);
    }

    private void addTile(Tile tile) {
        tiles.put(tile.getType().getPos(), tile);
        addEntity(tile);
    }

    public void addEntityBypass(YaegerEntity entity){
        addEntity(entity);
    }

    public void endTurn(){
        dobbelButton.setVisible(true);
        game.nextTurn();
    }

    public Game getGame() {
        return game;
    }

    public Map<Integer, Tile> getTiles() {
        return tiles;
    }

    public Button getDobbelButton() {
        return dobbelButton;
    }
}
