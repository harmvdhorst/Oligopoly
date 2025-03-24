package nl.harmjaydee.oligopoly.tiles;

import lombok.Getter;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.awt.*;

public abstract class Tile {

    @Getter
    private final Tiles type;
    @Getter
    private final Orientation orientation;

    public Tile(Tiles type, Orientation orientation) {
        this.type = type;
        this.orientation = orientation;
    }

    public abstract void draw(Graphics g);

    public abstract void use(GamePlayer player, String action);

}
