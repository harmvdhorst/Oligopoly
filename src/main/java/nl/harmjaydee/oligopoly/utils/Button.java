package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Button extends CompositeEntity implements MouseButtonPressedListener {

    private final Coordinate2D position;
    private final Size size;
    private final Runnable callback;
    private final String text;
    private final Color borderColor;
    private final Color backgroundColor;
    private final Color textColor;

    public Button(Coordinate2D pos, Size size, String text, Runnable callback) {
        super(new Coordinate2D(0, 0));
        this.position = pos;
        this.size = size;
        this.callback = callback;
        this.text = text;
        this.borderColor = Color.BLACK;
        this.backgroundColor = Color.WHITE;
        this.textColor = Color.BLACK;
    }

    public Button(Coordinate2D pos, Size size, Color borderColor, Color backgroundColor, Color textColor, String text, Runnable callback) {
        super(new Coordinate2D(0, 0));
        this.position = pos;
        this.size = size;
        this.callback = callback;
        this.text = text;
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    @Override
    public void onMouseButtonPressed(MouseButton btn, Coordinate2D pos) {
        if(btn == MouseButton.PRIMARY) {
            callback.run();
        }
    }

    @Override
    protected void setupEntities() {
        if(borderColor != null){
            addEntity(new RectangleWrapper(new Coordinate2D(position.getX() - (size.width() / 2) - 1, position.getY() - (size.height() / 2) - 1), new Size(size.width() + 2, size.height() + 2), borderColor));
        }
        addEntity(new RectangleWrapper(new Coordinate2D(position.getX() - (size.width() / 2), position.getY() - (size.height() / 2)), size, backgroundColor));
        TextEntity textEntity = new TextEntity(position, text);
        textEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        textEntity.setFont(Font.font("Roboto", size.height() / 2));
        textEntity.setFill(textColor);
        addEntity(textEntity);
    }

}
