package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.Coordinate2D;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;

import java.awt.*;
import java.util.List;

public class InfoMenu extends Menu {

    private List<PlayerTile> tiles;

    protected InfoMenu(Coordinate2D initialLocation) {
        super(initialLocation);
    }


    @Override
    public void setVisible(boolean visible) {

    }

    @Override
    protected void setupEntities() {

    }

}
