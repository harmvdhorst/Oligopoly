package nl.harmjaydee.oligopoly.utils;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Set;

public class TextInput extends DynamicCompositeEntity implements KeyListener, MouseButtonPressedListener {

    private final Coordinate2D pos;
    private final Size size;

    private boolean selected = false;
    private final StringBuilder sb = new StringBuilder();

    private final TextEntity text;

    public TextInput(Coordinate2D pos, Size size) {
        super(new Coordinate2D(0, 0));
        this.pos = pos;
        this.size = size;
        text = new TextEntity(new Coordinate2D(pos.getX() + 10 - (size.width() / 2) - 1, pos.getY() + (size.height() / 2) - (size.height() / 2) - 1), "");
        text.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Roboto", 20));
    }

    @Override
    protected void setupEntities() {
        addEntity(new RectangleWrapper(new Coordinate2D(pos.getX() - 1 - (size.width() / 2) - 1, pos.getY() - 1 - (size.height() / 2) - 1), new Size(size.width() + 2, size.height() + 2), Color.BLACK));
        addEntity(new RectangleWrapper(new Coordinate2D(pos.getX() - (size.width() / 2) - 1, pos.getY() - (size.height() / 2) - 1), size, Color.WHITE));
        addEntity(text);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> set) {
        if(selected) {
            if(set.contains(KeyCode.ESCAPE)) {
                selected = false;
                return;
            }
            if(set.contains(KeyCode.BACK_SPACE)) {
                if(!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return;
            }
            for (KeyCode key : set) {
                if(key.name().startsWith("DIGIT")){
                    String number = key.name().replaceAll("DIGIT","");
                    if(sb.length() == 2) {
                        return;
                    }
                    sb.append(number);
                }
            }
            text.setText(sb.toString());
        }
    }

    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D pos) {
        if(button == MouseButton.PRIMARY) {
            selected = true;
        }
    }

}
