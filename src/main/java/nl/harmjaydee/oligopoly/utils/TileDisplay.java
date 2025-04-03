package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;

import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileDisplay extends CompositeEntity {

    private final Coordinate2D pos;
    private final Size size;
    private final PlayerTile tile;

    public TileDisplay(Coordinate2D pos, Size size, PlayerTile tile) {
        super(new Coordinate2D(0, 0));
        this.tile = tile;
        this.pos = pos;
        this.size = size;
    }

    @Override
    protected void setupEntities() {
        addEntity(new RectangleWrapper(new Coordinate2D(pos.getX() - 1, pos.getY() - 1), new Size(size.width() + 2, size.height() + 2), Color.BLACK));
        addEntity(new RectangleWrapper(pos, size, Color.WHITE));

        addEntity(new RectangleWrapper(pos, new Size(size.width(), size.height() / 5), tile.getType().getColor().getColor()));

        int textX = (int) (pos.getX() + 10);

        List<TextEntity> textEntities = new ArrayList<>();

        TextEntity text1 = new TextEntity(new Coordinate2D(textX, 0),"Naam: " + tile.getType().getName());
        textEntities.add(text1);

        TextEntity text2 = new TextEntity(new Coordinate2D(textX, 0), "Waarde: " + tile.getType().getWorth());
        textEntities.add(text2);

        TextEntity text3 = new TextEntity(new Coordinate2D(textX, 0), "Huur: " + tile.getType().getRent());
        textEntities.add(text3);

        TextEntity text4 = new TextEntity(new Coordinate2D(textX, 0), "Eigenaar: " + (tile.getOwner() == -1 ? "Niemand" : "Speler " + tile.getOwner()));
        textEntities.add(text4);

        TextEntity text5 = new TextEntity(new Coordinate2D(textX, 0), "Aandelen: ");
        textEntities.add(text5);

        tile.getStocks().forEach((k, v) -> {
            TextEntity text6 = new TextEntity(new Coordinate2D(textX, 0), "     Speler " + k + ": " + v + "%");
            textEntities.add(text6);
        });

        addTexts(textEntities);
    }

    public void addTexts(List<TextEntity> texts) {
        for (int i = 0; i < texts.size(); i++) {
            TextEntity text = texts.get(i);
            text.setAnchorLocationY(pos.getY() + (size.height() / 5) + 5 + (i * 20));
            addEntity(text);
        }
    }

}
