package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

public class TileRectangle extends CompositeEntity {

    private final int tileHeight = 110;
    private final int tileWidth = 60;
    private final int sideOffset = 50;
    private final int tileColorHeight = 30;
    private final int boardWith = (9 * tileWidth) + (2 * tileHeight);

    private final Tiles type;

    public TileRectangle(Tiles tile) {
        super(new Coordinate2D(0, 0));
        type = tile;
    }

    private int getCalculatedWidth() {
        Orientation orientation = type.getOrientation();
        if(orientation == Orientation.UP || orientation == Orientation.DOWN) {
            return tileWidth;
        }
        return tileHeight;
    }

    private int getCalculatedHeight() {
        Orientation orientation = type.getOrientation();
        if(orientation == Orientation.LEFT || orientation == Orientation.RIGHT) {
            return tileWidth;
        }
        return tileHeight;
    }

    private int getCalculatedX(){
        if(type.getOrientation() == Orientation.RIGHT) {
            return sideOffset;
        }
        if(type.getOrientation() == Orientation.DOWN) {
            return (sideOffset + tileHeight) + ((type.getPos() - 11) * tileWidth);
        }
        if(type.getOrientation() == Orientation.LEFT) {
            return sideOffset + tileHeight + (9 * tileWidth);
        }
        if(type.getOrientation() == Orientation.UP) {
            return (sideOffset + tileHeight + (9 * tileWidth)) - (type.getPos() - tileColorHeight) * tileWidth;
        }
        if(type.getOrientation() == Orientation.CORNER) {
            if(type.getPos() == 0 || type.getPos() == 10) {
                return sideOffset;
            } else {
                return sideOffset + (boardWith - tileHeight);
            }
        }
        return 0;
    }

    private int getCalculatedY(){
        if(type.getOrientation() == Orientation.DOWN) {
            return sideOffset;
        }
        if(type.getOrientation() == Orientation.LEFT) {
            return sideOffset + tileHeight + (type.getPos() - 21) * tileWidth;
        }
        if(type.getOrientation() == Orientation.RIGHT) {
            return (boardWith + sideOffset) - tileHeight - (type.getPos()) * tileWidth;
        }
        if(type.getOrientation() == Orientation.UP) {
            return (boardWith + sideOffset) - tileHeight;
        }
        if(type.getOrientation() == Orientation.CORNER) {
            if(type.getPos() == 20 || type.getPos() == 10) {
                return sideOffset;
            } else {
                return sideOffset + (boardWith - tileHeight);
            }
        }
        return 0;
    }

    @Override
    protected void setupEntities() {

        int x = getCalculatedX();
        int y = getCalculatedY();
        int width = getCalculatedWidth();
        int height = getCalculatedHeight();
        Color color = Color.BLACK;

        addEntity(new RectangleWrapper(new Coordinate2D(x, y), new Size(width, height), color));


        int x2 = getCalculatedX() + 1;
        int y2 = getCalculatedY() + 1;
        int width2 = getCalculatedWidth() - 2;
        int height2 = getCalculatedHeight() - 2;
        Color color2 = Color.WHITE;

        addEntity(new RectangleWrapper(new Coordinate2D(x2, y2), new Size(width2, height2), color2));


        if(type.getWorth() != 0){
            int x3 = getCalculatedX() + 1;
            int y3 = getCalculatedY() + 1;
            int width3 = 0;
            int height3 = 0;
            Color color3 = type.getColor().getColor();

            if(type.getOrientation() == Orientation.LEFT || type.getOrientation() == Orientation.RIGHT) {
                width3 = tileHeight - (tileHeight - tileColorHeight) - 1;
                height3 = tileWidth - 2;

                if(type.getOrientation() == Orientation.RIGHT){
                    x3 += (tileHeight - tileColorHeight - 1);
                }
            } else {
                width3 = tileWidth - 2;
                height3 = tileHeight - (tileHeight - tileColorHeight) - 1;

                if(type.getOrientation() == Orientation.DOWN){
                    y3 += (tileHeight - tileColorHeight - 1);
                }
            }

            addEntity(new RectangleWrapper(new Coordinate2D(x3, y3), new Size(width3, height3), color3));
        }

        int x4 = getCalculatedX() + (getCalculatedWidth() / 2);
        int y4 = getCalculatedY() + (getCalculatedHeight() / 2);

        TextEntity text = new TextEntity(new Coordinate2D(x4, y4), type.getName());
        text.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        text.setFont(Font.font("Roboto", 6));
        addEntity(text);

    }

}
