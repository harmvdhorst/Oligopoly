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
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.utils.Button;
import nl.harmjaydee.oligopoly.utils.TextInput;
import nl.harmjaydee.oligopoly.utils.TileDisplay;

public class BuyStocksMenu extends Menu {

    private final Game game;
    private final GameScreen screen;
    private final PlayerTile tile;
    private final GamePlayer player;
    private TextInput textInput;
    private TextEntity bedrag;

    public BuyStocksMenu(GameScreen screen, PlayerTile tile, GamePlayer player) {
        super(screen);
        this.tile = tile;
        this.player = player;
        this.screen = screen;
        this.game = screen.getGame();
    }

    @Override
    public void addEntities() {
        addEntity(new TileDisplay(new Coordinate2D(screen.getWidth() / 2 - 60, 50 + 25), new Size(140, 240), tile));

        bedrag = new TextEntity(new Coordinate2D(screen.getWidth() / 2, screen.getHeight() / 2), "Bedrag: 0");
        bedrag.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        bedrag.setFont(Font.font("Roboto", 20));
        addEntity(bedrag);

        textInput = new TextInput(this, new Coordinate2D(screen.getWidth() / 2 - 200, screen.getHeight() / 2), new Size(100, 40));
        addEntity(textInput);

        addEntity(new Button(new Coordinate2D(screen.getWidth() / 2 + 200, screen.getHeight() / 2), new Size(100, 40), Color.BLACK, Color.WHITE, Color.BLACK, "Kopen", this::handleBuyButton));

        addEntity(new Button(new Coordinate2D(screen.getWidth() / 2, screen.getHeight() - 200), new Size(200, 40), Color.BLACK, Color.WHITE, Color.BLACK, "Beurt beeindigen", this::handleEndTurnButton));
    }

    public void update() {
        bedrag.setText("Bedrag: " + (tile.getType().getWorth() / 100 * textInput.getValue()));
    }

    private void handleBuyButton(){
        if(tile.buyStock(player, textInput.getValue())){
            screen.endTurn();
            remove();
        } else {
            // error
            screen.getGame().bankrupt(player);
        }
    }

    private void handleEndTurnButton(){
        screen.endTurn();
        remove();
    }

}
