package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.Coordinate2D;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.Tile;

public class BuyStocksMenu extends Menu {

    private final Tile tile;
    private final GamePlayer player;

    public BuyStocksMenu(Coordinate2D pos, Tile tile, GamePlayer player) {
        super(pos);
        this.tile = tile;
        this.player = player;
    }

    @Override
    public void setVisible(boolean visible) {

    }

    @Override
    protected void setupEntities() {

    }
}
