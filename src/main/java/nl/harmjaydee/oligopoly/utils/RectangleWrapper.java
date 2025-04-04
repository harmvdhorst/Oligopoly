package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;

public class RectangleWrapper extends RectangleEntity {

    public RectangleWrapper(Coordinate2D pos, Size size, Color color) {
        super(pos, size);
        setFill(color);
    }

}
