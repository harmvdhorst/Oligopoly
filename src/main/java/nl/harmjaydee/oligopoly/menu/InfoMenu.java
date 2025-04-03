package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.screen.GameScreen;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.utils.TileDisplay;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class InfoMenu extends Menu {

    private final List<Tile> tiles;

    public InfoMenu(GamePlayer player, GameScreen screen) {
        super(screen);
        tiles = screen.getTiles()
                .values()
                .stream()
                .filter(t -> t instanceof PlayerTile)
                .filter(t -> ((PlayerTile) t).getOwner() == player.getId())
                .collect(Collectors.toList());
    }

    @Override
    public void addEntities() {
        for (Tile t : tiles) {
            PlayerTile tile = (PlayerTile) t;

            TileDisplay display = new TileDisplay(new Coordinate2D(), new Size(0,0), tile);
            addEntity(display);
        }
    }

}
