package nl.harmjaydee.oligopoly.tiles.enums;

public enum Tiles {

    // corners
    START(0, "Start", Orientation.CORNER, 1, true),
    PARKEREN_1(10, "Pakeren", Orientation.CORNER, 1, true),
    PARKEREN_2(20, "Pakeren", Orientation.CORNER, 1, true),
    PARKEREN_3(30, "Pakeren", Orientation.CORNER, 1, true),

    // Kans kaarten
    KANS_1(2, "Kans", Orientation.RIGHT, 1),
    KANS_2(7, "Kans", Orientation.RIGHT, 1),
    KANS_3(12, "Kans", Orientation.DOWN, 1),
    KANS_4(17, "Kans", Orientation.DOWN, 1),
    KANS_5(22, "Kans", Orientation.LEFT, 1),
    KANS_6(28, "Kans", Orientation.LEFT, 1),
    KANS_7(33, "Kans", Orientation.UP, 1),
    KANS_8(36, "Kans", Orientation.UP, 1),

    // Stations
    STATION_1(5, "OV Fiets", Orientation.RIGHT, 1),
    STATION_2(15, "Bus", Orientation.DOWN, 1),
    STATION_3(25, "Tram", Orientation.LEFT, 1),
    STATION_4(35, "Trein", Orientation.UP, 1),

    // Belasting
    BELASTING_1(4, "Belasting", Orientation.RIGHT, 1),
    BELASTING_2(38, "Belasting", Orientation.UP, 1),

    // Brown
    APPELBOOM(1, "Appelboom", Color.BROWN, Orientation.RIGHT, 1, 1),
    DUBLOENDREEF(3, "Dubloendreef", Color.BROWN, Orientation.RIGHT, 1, 1),

    // Light Blue
    HAN(6, "HAN", Color.LIGHT_BLUE, Orientation.RIGHT, 1, 1),
    BURO302(8, "Buro 302", Color.LIGHT_BLUE, Orientation.RIGHT, 1, 1),
    ARNHEM_CENTRAAL(9, "Arnhem Centraal", Color.LIGHT_BLUE, Orientation.RIGHT, 1, 1),

    // Purple
    GOFFERTPARK(11, "Goffert Park", Color.PURPLE, Orientation.DOWN, 1, 1),
    KEIZERKAREL_PLEIN(13, "Keizerkarelplein", Color.PURPLE, Orientation.DOWN, 1, 1),
    LENT(14, "Lent", Color.PURPLE, Orientation.DOWN, 1, 1),

    // Orange
    UTRECHT_CENTRAAL(16, "Utrecht Centraal", Color.ORANGE, Orientation.DOWN, 1, 1),
    DOMTOREN(18, "Domtoren", Color.ORANGE, Orientation.DOWN, 1, 1),
    JAARBEURSPLEIN(19, "Jaarbeurs Plein", Color.ORANGE, Orientation.DOWN, 1, 1),

    // Red
    BUTJESSTRAAT(21, "Butjesstraat", Color.RED, Orientation.LEFT, 1, 1),
    WADDENEILANDEN(23, "Waddeneilanden", Color.RED, Orientation.LEFT, 1, 1),
    GULDENSTRAAT(24, "Guldenstraat", Color.RED, Orientation.LEFT, 1, 1),

    // Yellow
    HET_TORENTJE(26, "Het Torentje", Color.YELLOW, Orientation.LEFT, 1, 1),
    CATSHUIS(27, "Catshuis", Color.YELLOW, Orientation.LEFT, 1, 1),
    KOEKKOEKLAAN(29, "Kokkoeklaan", Color.YELLOW, Orientation.LEFT, 1, 1),

    // Green
    ROFFA_ZUID(31, "Roffa Zuid", Color.GREEN, Orientation.UP, 1, 1),
    ERASMUSBRUG(32, "Erasmusbrug", Color.GREEN, Orientation.UP, 1, 1),
    EUROMAST(34, "Euromast", Color.GREEN, Orientation.UP, 1, 1),

    // Blue
    JAVASTRAAT(37, "Javastraat", Color.BLUE, Orientation.UP, 1, 1),
    RED_LIGHT_DISTRICT(39,"Redlight District", Color.BLUE, Orientation.UP, 1, 1);

    private final int pos;
    private final String name;
    private Color color;
    private final Orientation orientation;
    private final int rent;
    private int worth = 0;
    private boolean corner = false;

    Tiles(int pos, String name, Orientation orientation, int rent){
        this.pos = pos;
        this.name = name;
        this.orientation = orientation;
        this.rent = rent;
    }

    Tiles(int pos, String name, Orientation orientation, int rent, boolean corner){
        this.pos = pos;
        this.name = name;
        this.orientation = orientation;
        this.rent = rent;
        this.corner = corner;
    }

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

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isCorner() {
        return corner;
    }
}
