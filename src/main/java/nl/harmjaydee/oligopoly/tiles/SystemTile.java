package nl.harmjaydee.oligopoly.tiles;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.awt.*;

public class SystemTile extends Tile {

    public SystemTile(Tiles type) {
        super(type, type.getOrientation());
    }

    @Override
    public void use(GamePlayer player, String action) {

    }

    @Override
    protected void setupEntities() {

    }

}
