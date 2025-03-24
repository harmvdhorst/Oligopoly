package nl.harmjaydee.oligopoly.tiles;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.awt.*;

public class SystemTile extends Tile {

    public SystemTile(Tiles type, Orientation orientation) {
        super(type, orientation);
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void use(GamePlayer player, String action) {

    }

}
