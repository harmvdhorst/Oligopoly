package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.harmjaydee.oligopoly.Game;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.screen.GameScreen;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.utils.Button;
import nl.harmjaydee.oligopoly.utils.TileDisplay;

public class BuyMenu extends Menu {

    private final Game game;
    private final PlayerTile tile;
    private final GamePlayer player;
    private final GameScreen screen;
    private final boolean buy;

    public BuyMenu(GameScreen gameScreen, PlayerTile tile, GamePlayer player, boolean buy) {
        super(gameScreen);
        this.tile = tile;
        this.player = player;
        this.screen = gameScreen;
        this.game = gameScreen.getGame();
        this.buy = buy;
    }

    @Override
    public void addEntities(){
        addEntity(new TileDisplay(new Coordinate2D(screen.getWidth() / 2 - 60, 50 + 25), new Size(140, 240), tile));

        TextEntity bedrag = new TextEntity(new Coordinate2D(screen.getWidth() / 2, screen.getHeight() / 2), "Bedrag: " + (buy ? tile.getType().getWorth() : tile.getType().getRent()));
        bedrag.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        bedrag.setFont(Font.font("Roboto", 20));
        addEntity(bedrag);

        addEntity(new Button(new Coordinate2D(screen.getWidth() / 2 - 200, screen.getHeight() / 2), new Size(100, 40), Color.BLACK, Color.WHITE, Color.BLACK, "Betaal", this::handlePayButton));

        if(!buy){
            if(tile.getOwner() != -1){
                addEntity(new Button(new Coordinate2D(screen.getWidth() / 2 + 200, screen.getHeight() / 2), new Size(100, 40), Color.BLACK, Color.WHITE, Color.BLACK, "Aandelen", this::handleStocksButton));
            }
        }

        addEntity(new Button(new Coordinate2D(screen.getWidth() / 2, screen.getHeight() - 200), new Size(200, 40), Color.BLACK, Color.WHITE, Color.BLACK, "Beurt beeindigen", this::handleEndTurn));
    }

    private void handlePayButton(){
        if(buy){
            if(tile.buy(player)){
                screen.endTurn();
                remove();
            } else {
                // failliet
                System.out.println("Failliet");
                screen.getGame().bankrupt(player);
            }
        } else {
            if(!tile.payRent(player)){
                System.out.println("Failliet");
                screen.getGame().bankrupt(player);
            }
        }
    }

    private void handleStocksButton(){
        BuyStocksMenu menu = new BuyStocksMenu(screen, tile, player);
        screen.addEntityBypass(menu);
        remove();
    }

    private void handleEndTurn(){
        if(buy){
            screen.endTurn();
            remove();
        }
    }

}
