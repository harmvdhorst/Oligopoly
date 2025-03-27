package nl.harmjaydee.oligopoly.screen;

import com.github.hanyaeger.api.scenes.DynamicScene;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.SystemTile;
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends DynamicScene {

    private Map<Integer, Tile> tiles = new HashMap<>();

    @Override
    public void setupScene() {

    }

    @Override
    public void setupEntities() {
        for (Tiles tile : Tiles.values()) {
            if(tile.getWorth() == 0){
                addTile(new SystemTile(tile));
            } else {
                addTile(new PlayerTile(tile));
            }
        }
        System.out.println("Loaded " + tiles.size() + " tiles");
    }

    private void addTile(Tile tile) {
        tiles.put(tile.getType().getPos(), tile);
        addEntity(tile);
    }

}
