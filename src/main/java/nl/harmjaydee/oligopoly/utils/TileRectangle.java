package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;
import nl.harmjaydee.oligopoly.tiles.enums.Tiles;

public class TileRectangle extends CompositeEntity {

    private Tiles type;

    public TileRectangle(Tiles tile) {
        super(new Coordinate2D(0, 0));
        type = tile;
    }

    private int getWidth(Orientation orientation) {
        if(orientation == Orientation.LEFT || orientation == Orientation.RIGHT) {
            return 110;
        }
        if(orientation == Orientation.UP || orientation == Orientation.DOWN) {
            return 60;
        }
        return 110;
    }

    private int getHeight(Orientation orientation) {
        if(orientation == Orientation.LEFT || orientation == Orientation.RIGHT) {
            return 60;
        }
        if(orientation == Orientation.UP || orientation == Orientation.DOWN) {
            return 110;
        }
        return 110;
    }

    private int getX(){
        if(type.getOrientation() == Orientation.RIGHT) {
            return 50;
        }
        if(type.getOrientation() == Orientation.DOWN) {
            return (50 + 110) + ((type.getPos() - 11) * 60);
        }
        if(type.getOrientation() == Orientation.LEFT) {
            return 50 + 110 + (9 * 60);
        }
        if(type.getOrientation() == Orientation.UP) {
            return (50 + 110 + (9 * 60)) - (type.getPos() - 30) * 60;
        }
        return 0;
    }

    private int getY(){
        if(type.getOrientation() == Orientation.DOWN) {
            return 50;
        }
        if(type.getOrientation() == Orientation.LEFT) {
            return 50 + 110 + (type.getPos() - 21) * 60;
        }
        if(type.getOrientation() == Orientation.RIGHT) {
            return 810 - 110 - (type.getPos()) * 60;
        }
        if(type.getOrientation() == Orientation.UP) {
            return 810 - 110;
        }
        return 0;
    }

    @Override
    protected void setupEntities() {
        RectangleEntity entity1 = new RectangleEntity(new Coordinate2D()) {};
        entity1.setWidth(getWidth(type.getOrientation()));
        entity1.setHeight(getHeight(type.getOrientation()));

        entity1.setAnchorLocation(new Coordinate2D(getX(), getY()));
        entity1.setFill(Color.BLACK);

        addEntity(entity1);

        RectangleEntity entity2 = new RectangleEntity(new Coordinate2D()) {};
        entity2.setWidth(getWidth(type.getOrientation()) - 2);
        entity2.setHeight(getHeight(type.getOrientation()) - 2);

        entity2.setAnchorLocation(new Coordinate2D(getX(), getY()));
        if(type.getOrientation() == Orientation.RIGHT || type.getOrientation() == Orientation.LEFT) {
            entity1.setAnchorLocation(new Coordinate2D(getX() - 1, getY() - 1));
        } else {
            entity1.setAnchorLocation(new Coordinate2D(getX() - 1, getY() - 1));
        }

        entity2.setFill(Color.WHITE);

        addEntity(entity2);

        RectangleEntity entity3 = new RectangleEntity(new Coordinate2D()) {};

        if(type.getOrientation() == Orientation.RIGHT || type.getOrientation() == Orientation.LEFT) {
            entity3.setWidth(110 - 80);
            entity3.setHeight(60);

            if(type.getOrientation() == Orientation.LEFT) {
                entity3.setAnchorLocation(new Coordinate2D(getX() - 1, getY() - 1));
            } else {
                entity3.setAnchorLocation(new Coordinate2D(getX() - 1 + 80, getY() - 1));
            }
        } else {
            entity3.setWidth(60);
            entity3.setHeight(110 - 80);

            if(type.getOrientation() == Orientation.UP) {
                entity3.setAnchorLocation(new Coordinate2D(getX() - 1, getY() - 1));
            } else {
                entity3.setAnchorLocation(new Coordinate2D(getX() - 1, getY() - 1 + 80));
            }
        }

        entity3.setFill(type.getColor().getColor());

        addEntity(entity3);
    }

}
