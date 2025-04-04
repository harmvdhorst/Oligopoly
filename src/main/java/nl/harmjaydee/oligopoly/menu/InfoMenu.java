package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import javafx.scene.paint.Color;
import nl.harmjaydee.oligopoly.GamePlayer;
import nl.harmjaydee.oligopoly.screen.GameScreen;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.Tile;
import nl.harmjaydee.oligopoly.utils.Button;
import nl.harmjaydee.oligopoly.utils.TileDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InfoMenu extends Menu {

    private final List<Tile> tiles;
    private final GameScreen screen;
    private List<TileDisplay> displays;

    private int current = 0;

    public InfoMenu(GamePlayer player, GameScreen screen) {
        super(screen);
        this.screen = screen;
        tiles = screen.getTiles()
                .values()
                .stream()
                .filter(t -> t instanceof PlayerTile)
                .filter(t -> ((PlayerTile) t).getOwner() == player.getId())
                .collect(Collectors.toList());
        displays = new ArrayList<>();
    }

    @Override
    public void addEntities() {
        for (Tile t : tiles) {
            PlayerTile tile = (PlayerTile) t;
            TileDisplay display = new TileDisplay(new Coordinate2D(screen.getWidth() / 2 - 70, screen.getHeight() / 2 - 120), new Size(140,240), tile);
            display.setVisible(false);
            addEntity(display);
            displays.add(display);
            if(!displays.isEmpty()){
                displays.get(0).setVisible(true);
            }
        }

        if(tiles.size() > 1) {
            addEntity(new Button(new Coordinate2D(screen.getWidth() / 2 - 180, screen.getHeight() / 2), new Size(50, 50), Color.BLACK, Color.WHITE, Color.BLACK, "<", this::handleBackButton));

            addEntity(new Button(new Coordinate2D(screen.getWidth() / 2 + 180, screen.getHeight() / 2), new Size(50, 50), Color.BLACK, Color.WHITE, Color.BLACK, ">", this::handleForewardButton));
        }

        addEntity(new Button(new Coordinate2D(screen.getWidth() / 2, screen.getHeight() / 2 + 250), new Size(200, 30), Color.BLACK, Color.WHITE, Color.BLACK, "Sluit", this::remove));
    }

    private void handleBackButton(){
        displays.get(current).setVisible(false);
        current--;
        if(current == -1) {
            current = displays.size() - 1;
        }
        displays.get(current).setVisible(true);
    }

    private void handleForewardButton(){
        displays.get(current).setVisible(false);
        current++;
        if(current == displays.size()) {
            current = 0;
        }
        displays.get(current).setVisible(true);
    }

}
