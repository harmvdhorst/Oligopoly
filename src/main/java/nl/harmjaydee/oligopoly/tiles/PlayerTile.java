package nl.harmjaydee.oligopoly.tiles;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.awt.*;
import java.util.Map;

public class PlayerTile extends Tile {

    private Map<Integer, Integer> stocks;
    private int owner;

    public PlayerTile(Tiles type, Orientation orientation) {
        super(type, orientation);
    }

    @Override
    public void use(GamePlayer player, String action) {

    }

    public void changeOwner(GamePlayer player) {

    }

    public void buy(GamePlayer player) {

    }

    public void buyStock(GamePlayer player, int stocks) {

    }

}
