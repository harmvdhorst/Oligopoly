package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;
import javafx.scene.paint.Color;
import nl.harmjaydee.oligopoly.utils.RectangleWrapper;

public abstract class Menu extends CompositeEntity {

    private final YaegerScene screen;

    protected Menu(YaegerScene scene) {
        super(new Coordinate2D(0, 0));
        this.screen = scene;
    }

    @Override
    protected void setupEntities() {
        addEntity(new RectangleWrapper(new Coordinate2D(50, 50), new Size(screen.getWidth() - 100, screen.getHeight() - 100), Color.WHITE));
        addEntities();
    }

    public abstract void addEntities();
}
