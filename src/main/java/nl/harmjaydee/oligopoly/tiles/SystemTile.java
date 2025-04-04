package nl.harmjaydee.oligopoly.tiles;

import com.github.hanyaeger.api.entities.Collider;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.screen.GameScreen;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;
import nl.harmjaydee.oligopoly.utils.TileRectangle;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class SystemTile extends Tile implements Collider {

    private final Tiles type;
    private final GameScreen screen;

    public SystemTile(GameScreen screen, Tiles type) {
        super(type, type.getOrientation());
        this.type = type;
        this.screen = screen;
    }

    @Override
    public void use(GamePlayer player) {
        if(type == Tiles.START){
            player.depositMoney(200);
            screen.endTurn();
            return;
        }
        if(type.name().startsWith("PARKEREN")){
            int bank = screen.getGame().getBankBalance();
            player.depositMoney(bank);
            screen.getGame().removeBankBalance(bank);
            screen.endTurn();
            return;
        }
        if(type.name().startsWith("STATION")){
            int random = ThreadLocalRandom.current().nextInt(1, 4);
            if(random > 2){
                player.depositMoney(100);
            } else {
                boolean success = player.withdrawMoney(100);
                if(!success){
                    // failiet
                    System.out.println("Failliet");
                    screen.getGame().bankrupt(player);
                }
            }
            screen.endTurn();
            return;
        }
        if(type.name().startsWith("KANS")){
            // Not implemented
            screen.endTurn();
            return;
        }
        if(type.name().startsWith("BELASTING")){
            int rent = type.getRent();
            boolean success = player.withdrawMoney(rent);
            if(!success){
                // failiet
                System.out.println("Failliet");
                screen.getGame().bankrupt(player);
            }
            screen.endTurn();
            return;
        }
    }

    @Override
    protected void setupEntities() {
        addEntity(new TileRectangle(type));
    }

    public String toString() {
        return "SystemTile{type=" + type + "}";
    }
}
