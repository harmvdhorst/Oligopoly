package nl.harmjaydee.oligopoly.tiles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

public abstract class Tile extends DynamicCompositeEntity {

    private final Tiles type;
    private final Orientation orientation;

    public Tile(Tiles type, Orientation orientation) {
        super(new Coordinate2D(0, 0));
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
