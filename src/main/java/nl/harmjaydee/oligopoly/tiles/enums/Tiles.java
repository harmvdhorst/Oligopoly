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

    RED_LIGHT_DISTRICT(1,"Redlight District", Color.BLUE, 1000);

    private final int pos;
    private final String name;
    private final Color color;
    private final int rent;

    Tiles(int pos, String name, Color color, int rent) {
        this.pos = pos;
        this.name = name;
        this.color = color;
        this.rent = rent;
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
}
