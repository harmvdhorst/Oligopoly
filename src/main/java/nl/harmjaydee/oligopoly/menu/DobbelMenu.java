package nl.harmjaydee.oligopoly.menu;

import com.github.hanyaeger.api.*;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nl.harmjaydee.oligopoly.screen.GameScreen;
import nl.harmjaydee.oligopoly.utils.RectangleWrapper;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DobbelMenu extends DynamicCompositeEntity implements TimerContainer {

    private DobbelMenu dobbelMenu;

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    private TextEntity textEntity;
    private GameScreen screen;

    private int rotations = 0;

    private int thrown = 0;

    public DobbelMenu(GameScreen screen) {
        super(new Coordinate2D(0, 0));
        this.screen = screen;
        this.dobbelMenu = this;
    }

    @Override
    public void setupEntities() {
        addEntity(new RectangleWrapper(new Coordinate2D(200, 200), new Size(screen.getWidth() - 400, screen.getHeight() - 400), Color.WHITE));

        textEntity = new TextEntity(new Coordinate2D(screen.getWidth() / 2, screen.getHeight() /2 ), "1 + 1");
        textEntity.setFont(Font.font("Roboto", 25));
        textEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(textEntity);
    }

    @Override
    public void setupTimers() {
        addTimer(new Timer(100) {
            @Override
            public void onAnimationUpdate(long l) {
                if(rotations >= 40){
                    screen.getDobbelButton().setVisible(false);
                    screen.getGame().setLastThrown(thrown);
                    dobbelMenu.remove();
                    remove();
                } else {
                    int random1 = random.nextInt(1, 6);
                    int random2 = random.nextInt(1, 6);

                    rotations++;

                    if(rotations >= 20) {
                        if(thrown == 0){
                            thrown = random1 + random2;
                        }
                        textEntity.setText("Je hebt " + thrown + " gegooid!");
                        textEntity.setAnchorPoint(AnchorPoint.CENTER_CENTER);
                        return;
                    }

                    textEntity.setText(random1 + " + " + random2);
                }
            }
        });
    }

}
