package nl.harmjaydee.oligopoly.tiles.enums;

public enum Tiles {

    // Brown

    // Light Blue

    // Purple

    // Orange

    // Red

    // Yellow

    // Green

    // Blue

    RED_LIGHT_DISTRICT(1,"Redlight District", Color.BLUE, Orientation.LEFT, 1000, 1000);

    private final int pos;
    private final String name;
    private final Color color;
    private final Orientation orientation;
    private final int rent;
    private final int worth;

    Tiles(int pos, String name, Color color, Orientation orientation, int worth, int rent) {
        this.pos = pos;
        this.name = name;
        this.color = color;
        this.rent = rent;
        this.worth = worth;
        this.orientation = orientation;
    }

    public int getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getRent() {
        return rent;
    }

    public int getWorth() {
        return worth;
    }
}
