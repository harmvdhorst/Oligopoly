package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;
import nl.harmjaydee.oligopoly.tiles.enums.Orientation;

import java.awt.*;

public class RectangleWrapper extends RectangleEntity {

    public RectangleWrapper(Coordinate2D initialLocation, Size size, Color color) {
        super(initialLocation, size);
        setFill(color);
    }

    public RectangleWrapper(Coordinate2D location, Orientation orientation, Color color) {
        super(location);
        setWidth(getWidth(orientation));
        setHeight(getHeight(orientation));
        setFill(color);
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
        return 60;
    }

}
