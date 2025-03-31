package nl.harmjaydee.oligopoly.tiles;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.TileRectangle;

import java.awt.*;

public class SystemTile extends Tile {

    private Tiles type;

    public SystemTile(Tiles type) {
        super(type, type.getOrientation());
        this.type = type;
    }

    @Override
    public void use(GamePlayer player, String action) {

    }

    @Override
    protected void setupEntities() {
        addEntity(new TileRectangle(type));
    }

}
