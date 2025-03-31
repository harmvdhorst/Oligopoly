package nl.harmjaydee.oligopoly.tiles;

import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.TileRectangle;

import java.util.HashMap;
import java.util.Map;

public class PlayerTile extends Tile {

    private final Map<Integer, Integer> stocks;
    private int owner = 0;
    private final Tiles type;

    public PlayerTile(Tiles type) {
        super(type, type.getOrientation());
        this.type = type;
        this.stocks = new HashMap<>();
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
        addEntity(new TileRectangle(this.getType()));
    }

}
