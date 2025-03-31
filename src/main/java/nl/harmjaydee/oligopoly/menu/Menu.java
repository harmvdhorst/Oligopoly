package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;

public abstract class Menu extends DynamicCompositeEntity {

    protected Menu(Coordinate2D initialLocation) {
        super(initialLocation);
    }

    public abstract void setVisible(boolean visible);

}
