public class Player {
    private java.awt.Color colour;

    private static final java.awt.Color WHITE_COLOR = java.awt.Color.RED;

    public Player(java.awt.Color colour) {
        this.colour = colour;
    }

    public boolean isWhite() {
        return this.colour.equals(WHITE_COLOR);
    }
}
