package nl.harmjaydee.oligopoly.menu;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.Tile;

import java.awt.*;

public class BuyMenu extends Menu{

    private final Tile tile;
    private final GamePlayer player;

    public BuyMenu(Tile tile, GamePlayer player) {
        this.tile = tile;
        this.player = player;
    }

    @Override
    public void draw(Graphics g) {

    }

}
