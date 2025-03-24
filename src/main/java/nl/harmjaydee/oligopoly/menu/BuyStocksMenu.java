package nl.harmjaydee.oligopoly.menu;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.ILoopable;
import nl.harmjaydee.oligopoly.tiles.Tile;

import java.awt.*;

public class BuyStocksMenu extends Menu implements ILoopable {

    private final Tile tile;
    private final GamePlayer player;

    public BuyStocksMenu(Tile tile, GamePlayer player) {
        this.tile = tile;
        this.player = player;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void loop(){

    }

}
