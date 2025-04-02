package nl.harmjaydee.oligopoly.screen;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.entities.impl.CustomFont;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.StaticScene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.harmjaydee.oligopoly.Game;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.menu.BuyMenu;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.SystemTile;
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.Button;
import nl.harmjaydee.oligopoly.utils.RectangleWrapper;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends DynamicScene {

    private Game game;
    private Map<Integer, Tile> tiles = new HashMap<>();

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
                addTile(new SystemTile(tile));
            } else {
                addTile(new PlayerTile(game, tile));
            }
        }
        addEntity(new RectangleWrapper(new Coordinate2D(50 + 110 + 1, 50 + 110 + 1), new Size(540 - 2, 540 - 2), Color.WHITE));

        addEntity(new GamePlayer(1, "sprites/dummy.png", new Coordinate2D(53, 718)));
      
        TextEntity text = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() / 2), "Oligopoly");
        text.setFont(Font.font("Roboto", 30));
        text.setStrokeColor(Color.BLACK);
        text.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        addEntity(text);

        System.out.println("Loaded " + tiles.size() + " tiles");

        PlayerTile tile = new PlayerTile(game, Tiles.RED_LIGHT_DISTRICT);
        tile.getStocks().put(0, 20);
        tile.getStocks().put(1, 30);
        tile.getStocks().put(2, 25);
        tile.getStocks().put(3, 25);

        Button button = new Button(new Coordinate2D(getWidth() / 2, getHeight() / 2), new Size(100, 50), Color.BLACK, Color.WHITE, Color.BLACK, "Test", () -> {
            BuyMenu menu = new BuyMenu(this, tile,  null);
            addEntity(menu);
        });

        addEntity(button);
    }

    private void addTile(Tile tile) {
        tiles.put(tile.getType().getPos(), tile);
        addEntity(tile);
    }

    public void addEntityBypass(YaegerEntity entity){
        addEntity(entity);
    }

    public Game getGame() {
        return game;
    }

    public Map<Integer, Tile> getTiles() {
        return tiles;
    }
}
