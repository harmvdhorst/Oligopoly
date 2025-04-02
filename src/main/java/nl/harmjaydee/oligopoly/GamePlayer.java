package nl.harmjaydee.oligopoly;

import java.util.List;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import nl.harmjaydee.oligopoly.tiles.PlayerTile;
import nl.harmjaydee.oligopoly.tiles.Tile;

import java.util.Random;
import java.util.Set;


public class GamePlayer extends DynamicSpriteEntity implements KeyListener, Collider, Collided, UpdateExposer {

    private int id;
    private int balance;
    private int currentPosition;

    public GamePlayer(int id, String resource, Coordinate2D location) {
        super(resource, location, new Size(20, 40), 1, 2);
        this.id = id;
    }

    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {

            if (pressedKeys.contains(KeyCode.LEFT)) {
                setMotion(3, 270d);
                setCurrentFrameIndex(0);
            } else if (pressedKeys.contains(KeyCode.RIGHT)) {
                setMotion(3, 90d);
                setCurrentFrameIndex(1);
            } else if (pressedKeys.contains(KeyCode.UP)) {
                setMotion(3, 180d);
            } else if (pressedKeys.contains(KeyCode.DOWN)) {
                setMotion(3, 0d);
            } else if (pressedKeys.isEmpty()) {
                setSpeed(0);
            }
        }

    public void depositMoney(int amount) {
        balance += amount;
    }

    public boolean withdrawMoney(int amount) {
        if(amount > balance){
            return false;
        }
        balance -= amount;
        return true;
    }

    public int getId() {
        return id;
    }

    public void onCollision(List<Collider> collidingEntities) {

            System.out.println("Collision detected with entities: " + collidingEntities);

    }

    @Override
    public void explicitUpdate(long l) {


        double playerX = getAnchorLocation().getX();
        double playerY = getAnchorLocation().getY();
        if(playerY <= 702 && playerY >= 700 && playerX >= 140 && playerX <= 700 || playerY <= 50) {
           setAnchorLocationY(playerY + 3);
        } else if (playerY >= 780) {
            setAnchorLocationY(playerY - 3);
        }else if(playerY <= 132 && playerY >= 130 && playerX >= 140 && playerX <= 700) {
            setAnchorLocationY(playerY - 3);
        }
        // Check if player reaches the right side boundary or a specific position
        if (playerX >= 140 && playerX <= 142 && playerY >= 130 && playerY <= 700 || playerX >= 790) {
            setAnchorLocationX(playerX - 3); // Move left
        }
        // Move player back by 20 if on the left side
        else if (playerX <= 51 || playerX >= 700 && playerX <= 702 && playerY >= 130 && playerY <= 700) {
            setAnchorLocationX(playerX + 3); // Move right
        }
    }
}
