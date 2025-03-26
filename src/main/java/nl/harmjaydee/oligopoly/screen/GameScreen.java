package nl.harmjaydee.oligopoly.screen;

import com.github.hanyaeger.api.scenes.DynamicScene;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.util.Map;

public class GameScreen extends DynamicScene {

    private Map<Integer, Tile> tiles;

    @Override
    public void setupScene() {

    }

    @Override
    public void setupEntities() {
        for (Tiles tile : Tiles.values()) {
            addEntity(new PlayerTile(tile, Orientation.RIGHT));
        }
    }

}
