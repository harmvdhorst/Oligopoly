package nl.harmjaydee.oligopoly.tiles;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.IDrawable;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.awt.*;

public abstract class Tile implements IDrawable {

    private final Tiles type;
    private final Orientation orientation;

    public Tile(Tiles type, Orientation orientation) {
        this.type = type;
        this.orientation = orientation;
    }

    public abstract void use(GamePlayer player, String action);

    public Tiles getType() {
        return type;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
