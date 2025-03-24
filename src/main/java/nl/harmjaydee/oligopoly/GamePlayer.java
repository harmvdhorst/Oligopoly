package nl.harmjaydee.oligopoly;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public class GamePlayer extends DynamicSpriteEntity {

    private int balance;
    private int currentPosition;

    protected GamePlayer(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }

    public void movePlaces(int amount, boolean foreward) {

    }

    public void depositMoney(int amount) {

    }

    public void withdrawMoney(int amount) {

    }


}
