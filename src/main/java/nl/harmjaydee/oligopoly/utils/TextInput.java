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
import nl.harmjaydee.oligopoly.menu.BuyStocksMenu;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TextInput extends DynamicCompositeEntity implements KeyListener, MouseButtonPressedListener {

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final BuyStocksMenu menu;

    private final Coordinate2D pos;
    private final Size size;

    private boolean selected = false;
    private final StringBuilder sb = new StringBuilder();

    private final TextEntity text;

    private boolean wasClicked = false;

    public TextInput(BuyStocksMenu menu, Coordinate2D pos, Size size) {
        super(new Coordinate2D(0, 0));
        this.pos = pos;
        this.size = size;
        text = new TextEntity(new Coordinate2D(pos.getX() + 10 - (size.width() / 2) - 1, pos.getY() + (size.height() / 2) - (size.height() / 2) - 1), "");
        text.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Roboto", 20));
        this.menu = menu;
    }

    @Override
    protected void setupEntities() {
        addEntity(new RectangleWrapper(new Coordinate2D(pos.getX() - 1 - (size.width() / 2) - 1, pos.getY() - 1 - (size.height() / 2) - 1), new Size(size.width() + 2, size.height() + 2), Color.BLACK));
        addEntity(new RectangleWrapper(new Coordinate2D(pos.getX() - (size.width() / 2) - 1, pos.getY() - (size.height() / 2) - 1), size, Color.WHITE));
        addEntity(text);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> set) {
        if(selected && !wasClicked) {
            if(set.contains(KeyCode.ESCAPE)) {
                selected = false;
            }
            if(set.contains(KeyCode.BACK_SPACE)) {
                if(!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            for (KeyCode key : set) {
                if(key.name().startsWith("DIGIT")){
                    String number = key.name().replaceAll("DIGIT","");
                    int digit = Integer.parseInt(number);
                    if(sb.length() == 2 || digit > (sb.length() == 1 ? 5 : 2)) {
                        return;
                    }
                    sb.append(number);
                }
            }
            text.setText(sb.toString());
            wasClicked = true;
            menu.update();

            executor.schedule(() -> {
                wasClicked = false;
            }, 200, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D pos) {
        if(button == MouseButton.PRIMARY) {
            selected = true;
        }
    }

    public int getValue() {
        if(sb.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(sb.toString());
    }

}
