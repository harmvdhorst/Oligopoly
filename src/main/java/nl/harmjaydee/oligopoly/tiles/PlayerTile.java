package nl.harmjaydee.oligopoly.tiles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

import java.util.Map;

public class PlayerTile extends Tile {

    private Map<Integer, Integer> stocks;
    private int owner;
    private Tiles type;

    public PlayerTile(Tiles type, Orientation orientation) {
        super(type, orientation);
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
        addEntity(new RectangleEntity(new Coordinate2D(100, 100), new Size(100, 100)) {});
    }

}
