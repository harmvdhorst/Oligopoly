package nl.harmjaydee.oligopoly.functions;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cardList;

    public Cards(String type) {
        this.cardList = new ArrayList<>();
    }

    private void CreateCards() {
      cardList.add(new Card("ga naar redlight district"));
    }

    public List<Card> getCards() {
        return new ArrayList<>(cardList);
    }

}
