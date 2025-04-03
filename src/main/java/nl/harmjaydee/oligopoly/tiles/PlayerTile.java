package nl.harmjaydee.oligopoly.tiles;

import com.github.hanyaeger.api.entities.Collider;
import nl.harmjaydee.oligopoly.Game;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.TileRectangle;

import java.util.HashMap;
import java.util.Map;

public class PlayerTile extends Tile implements Collider {

    private final Map<Integer, Integer> stocks;
    private int owner = 0;
    private final Tiles type;

    public PlayerTile(Game game, Tiles type) {
        super(type, type.getOrientation());
        this.type = type;
        this.stocks = new HashMap<>();
    }

    @Override
    public void use(GamePlayer player, String action) {
        switch (action) {
            case "buy":
                break;
        }

    }

    public void changeOwner(GamePlayer player) {
        this.owner = player.getId();
    }

    public boolean payRent(GamePlayer player) {
        int rent = this.getType().getRent();

        if(!player.withdrawMoney(rent)) {
            return false;
        }

        stocks.forEach((playerId, stockAmount) -> {
            GamePlayer playerToPay = game.getPlayer(playerId);
            playerToPay.depositMoney((rent / 100) * stockAmount);
        });

        return true;
    }

    public boolean buy(GamePlayer player) {
        boolean success = player.withdrawMoney(type.getWorth());
        if (success) {
            this.changeOwner(player);
        }
        return success;
    }

    public boolean buyStock(GamePlayer player, int stocks) {

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

        // check if the maximum of 100 stocks is hit
        if((this.stocks.get(player.getId()) + stocks) > 100) {
            return false;
        }

        // try to withdraw the owed amount
        int price = (type.getWorth() / 100) * stocks;
        boolean success = player.withdrawMoney(price);

        if (success) {
            // add the player if they dont have any stocks
            this.stocks.putIfAbsent(player.getId(), 0);

            // add the stocks to the new owner and remove them from the old owner
            this.stocks.compute(player.getId(), (k, oldStocks) -> oldStocks + stocks);
            this.stocks.compute(mostStocks, (k, oldStocks) -> oldStocks - stocks);

            // pay out old owner + pay the bank
            GamePlayer oldOwner = game.getPlayer(mostStocks);
            if(oldOwner != null) {
                oldOwner.depositMoney(price / 2);
            }

            game.addBankBalance(price / 2);

        }

        // if the new owner owns all stocks transfer ownership
        if (this.stocks.get(player.getId()) == 100) {
            this.changeOwner(player);
        }

        return success;

    }

    @Override
    protected void setupEntities() {
        addEntity(new TileRectangle(this.getType()));
    }


    public int getOwner() {
        return this.owner;
    }

    public Map<Integer, Integer> getStocks() {
        return stocks;
    }

}
