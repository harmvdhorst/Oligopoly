package nl.harmjaydee.oligopoly.functions;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cardList;
    public Cards(String type) {
        this.cardList = new ArrayList<>();
    }
    private void CreateCards() {
    // voeg kaarten toe
    }

    public List<Card> getCards() {
        return new ArrayList<>(cardList);
    }
    public void draw(Graphics g) {

    }
}
