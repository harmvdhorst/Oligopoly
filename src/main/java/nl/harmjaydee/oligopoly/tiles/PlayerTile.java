package nl.harmjaydee.oligopoly.tiles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.RectangleWrapper;
import nl.harmjaydee.oligopoly.utils.TileRectangle;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerTile extends Tile {

    private Map<Integer, Integer> stocks;
    private int owner;
    private Tiles type;

    public PlayerTile(Tiles type) {
        super(type, type.getOrientation());
        this.type = type;
    }

    @Override
    public void use(GamePlayer player, String action) {
        
    }

    public void changeOwner(GamePlayer player) {
        this.owner = player.getId();
    }

    public void buy(GamePlayer player) {
        boolean success = player.withdrawMoney(type.getWorth());
        if(success) {
            this.changeOwner(player);
        }
    }

    public void buyStock(GamePlayer player, int stocks) {

        // check what player has the most amount of stocks
        int mostStocks = 0;
        int stockAmount = 0;

        for (Map.Entry<Integer, Integer> entry : this.stocks.entrySet()) {
            int playerId = entry.getKey();
            int playerStocks = entry.getValue();

            if(playerId != player.getId()) {
                if(playerStocks > mostStocks) {
                    mostStocks = playerId;
                    stockAmount = playerStocks;
                }
            }
        }

        // try to withdraw the owed amount
        int price = (type.getWorth() / 100) * stocks;
        boolean success = player.withdrawMoney(price);

        if(success) {
            // add the player if they dont have any stocks
            this.stocks.putIfAbsent(player.getId(), 0);

            // check if the maximum of 100 stocks is hit
            if((this.stocks.get(player.getId()) + stocks) > 100) {
                return;
            }

            // add the stocks to the new owner and remove them from the old owner
            this.stocks.compute(player.getId(), (k, oldStocks) -> oldStocks + stocks);
            this.stocks.compute(mostStocks, (k, oldStocks) -> oldStocks - stocks);

            // pay out old owner + pay the bank
            // TODO

        }

        // if the new owner owns all stocks transfer ownership
        if(this.stocks.get(player.getId()) == 100) {
            this.changeOwner(player);
        }


    }

    @Override
    protected void setupEntities() {
//        int x = 60 * (type.getPos() - 10);
//        int y = 20;
//
//        if(getOrientation() != Orientation.DOWN) return;
//
//        addEntity(new RectangleWrapper(new Coordinate2D(x,y), type.getOrientation(), Color.BLACK));
//        addEntity(new RectangleWrapper(new Coordinate2D(x + 1,y + 1), new Size(58, 108), Color.WHITE));
//        addEntity(new RectangleWrapper(new Coordinate2D(x + 1,y + 1), new Size(58, 30), Color.RED));
        addEntity(new TileRectangle(this.getType()));
    }

}
