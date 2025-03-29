package nl.harmjaydee.oligopoly.tiles.enums;

public enum Color {

    BROWN(2, javafx.scene.paint.Color.BROWN),
    LIGHT_BLUE(3, javafx.scene.paint.Color.LIGHTBLUE),
    PURPLE(3, javafx.scene.paint.Color.PURPLE),
    ORANGE(3, javafx.scene.paint.Color.ORANGE),
    RED(3, javafx.scene.paint.Color.RED),
    YELLOW(3, javafx.scene.paint.Color.YELLOW),
    GREEN(3, javafx.scene.paint.Color.GREEN),
    BLUE(2, javafx.scene.paint.Color.BLUE),;

    private final int maxTiles;
    private final javafx.scene.paint.Color color;

    Color(int maxTiles, javafx.scene.paint.Color color) {
        this.maxTiles = maxTiles;
        this.color = color;
    }

    public int getMaxTiles() {
        return maxTiles;
    }

    public javafx.scene.paint.Color getColor() {
        return color;
    }
}
