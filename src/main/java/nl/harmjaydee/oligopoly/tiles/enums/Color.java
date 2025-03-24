package nl.harmjaydee.oligopoly.tiles.enums;

public enum Color {

    BROWN(2),
    LIGHT_BLUE(3),
    PURPLE(3),
    ORANGE(3),
    RED(3),
    YELLOW(3),
    GREEN(3),
    BLUE(2);

    private final int maxTiles;

    Color(int maxTiles) {
        this.maxTiles = maxTiles;
    }

    public int getMaxTiles() {
        return maxTiles;
    }

}
