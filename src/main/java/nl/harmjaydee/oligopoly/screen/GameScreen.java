package nl.harmjaydee.oligopoly.screen;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.SystemTile;
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.RectangleWrapper;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends DynamicScene {

    private Map<Integer, Tile> tiles = new HashMap<>();

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
                addTile(new PlayerTile(tile));
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
    }

    private void addTile(Tile tile) {
        tiles.put(tile.getType().getPos(), tile);
        addEntity(tile);
    }

}
