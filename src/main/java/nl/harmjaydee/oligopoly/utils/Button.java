package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import javafx.scene.input.MouseButton;


public class Button extends CompositeEntity implements MouseButtonPressedListener {

    private final Coordinate2D position;
    private final Size size;
    private final Runnable callback;
    private final String text;

    public Button(Coordinate2D pos, Size size, String text, Runnable callback) {
        super(pos);
        this.position = pos;
        this.size = size;
        this.callback = callback;
        this.text = text;
    }

    @Override
    public void onMouseButtonPressed(MouseButton btn, Coordinate2D pos) {
        if(btn == MouseButton.PRIMARY) {
            callback.run();
        }
    }

    @Override
    protected void setupEntities() {
        addEntity(new RectangleEntity(position, size) {});
        addEntity(new TextEntity(position, text));
    }

}
