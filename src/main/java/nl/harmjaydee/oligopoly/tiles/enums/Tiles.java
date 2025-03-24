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

    RED_LIGHT_DISTRICT("Redlight District", Color.BLUE, 1000);

    private final String name;
    private final Color color;
    private final int rent;

    Tiles(String name, Color color, int rent) {
        this.name = name;
        this.color = color;
        this.rent = rent;
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
