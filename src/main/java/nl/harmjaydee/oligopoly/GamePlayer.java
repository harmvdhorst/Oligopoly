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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class GamePlayer extends DynamicSpriteEntity implements KeyListener, Collider, Collided, UpdateExposer {
    private final Game game;
    private final int id;

    private int balance = 1500;

    private int currentPosition = 0;
    private boolean isOnRightTile = false;
    private int tileToGo = 1;

    private boolean isAllowedToMove = false;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public GamePlayer(Game game, int id, String resource, Coordinate2D location) {
        super(resource, location, new Size(40, 40));
        this.id = id;
        this.game = game;
    }

    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (game.getCurrentPlayer() == this && isAllowedToMove) {
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

        if(game.getCurrentPlayer() == this){

            List<Collider> newColliders = collidingEntities.stream().filter(c -> c instanceof Tile).toList();

            if(!isOnRightTile) {
                if(!newColliders.isEmpty()) {
                    Tile tile = (Tile) newColliders.getFirst();
                    if(tile.getType().getPos() == tileToGo){
                        isOnRightTile = true;
                        scheduler.schedule(() -> {
                            isAllowedToMove = false;
                            setMotion(0, 0);
                            currentPosition = tileToGo;
                            tile.use(this);
                        }, 147, TimeUnit.MILLISECONDS);
                    }
                }
            }
        }

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

    public void setOnRightTile(boolean onRightTile) {
        isOnRightTile = onRightTile;
    }

    public void addGoal(int amount) {
        this.tileToGo = currentPosition + amount;
        if(tileToGo >= 40){
            tileToGo -= 40;
        }
        isAllowedToMove = true;
    }

    public int getBalance() {
        return balance;
    }

    public String toString() {
        return "GamePlayer=" + id ;
    }

    public Integer getTileToGo() {
        return tileToGo;
    }

}
