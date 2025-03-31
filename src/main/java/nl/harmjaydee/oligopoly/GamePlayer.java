package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;


public class GamePlayer extends DynamicSpriteEntity {

    private int id;
    private int balance;
    private int currentPosition;

    protected GamePlayer(int id, String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
        this.id = id;
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



