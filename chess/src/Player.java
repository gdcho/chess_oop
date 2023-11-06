/**
 * Represents a player in the chess game. Each player is differentiated by color.
 */
public class Player {
    // The color assigned to the player.
    private java.awt.Color colour;

    private static final java.awt.Color WHITE_COLOR = java.awt.Color.RED;

    /**
     * Constructs a new Player with the specified color.
     *
     * @param colour The color representing the player.
     */
    public Player(java.awt.Color colour) {
        this.colour = colour;
    }

    /**
     * Determines if the player is the one designated as 'White' in the game,
     * based on the assigned color being equal to the predefined WHITE_COLOR.
     *
     * @return true if this player is the white player, false otherwise.
     */
    public boolean isWhite() {
        return this.colour.equals(WHITE_COLOR);
    }
}
