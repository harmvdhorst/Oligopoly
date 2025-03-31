package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;

import java.util.Set;


public class GamePlayer extends DynamicSpriteEntity implements KeyListener {

    private int id;
    private int balance;
    private int currentPosition;

    public GamePlayer(int id, String resource, Coordinate2D location){
        super(resource, location,new Size(20,40), 1, 2);
        this.id = id;
    }
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if(pressedKeys.contains(KeyCode.LEFT)){
            setMotion(3,270d);
            setCurrentFrameIndex(0);
        } else if(pressedKeys.contains(KeyCode.RIGHT)){
            setMotion(3,90d);
            setCurrentFrameIndex(1);
        } else if(pressedKeys.contains(KeyCode.UP)){
            setMotion(3,180d);
        } else if(pressedKeys.contains(KeyCode.DOWN)){
            setMotion(3,0d);
        } else if(pressedKeys.isEmpty()){
            setSpeed(0);
        }
    }



    public void depositMoney(int amount) {

    }

    public boolean withdrawMoney(int amount) {
        return false;
    }

    public int getId() {
        return id;
    }
}



