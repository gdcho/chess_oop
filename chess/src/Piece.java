import java.util.List;

/**
 * The abstract class Piece serves as a template for all the chess pieces.
 * It contains the common attributes and methods that all chess pieces share.
 */
public abstract class Piece {
    private String image;
    private Player owner;
    private Square square;

    /**
     * Constructs a Piece object with an owner and image identifier.
     *
     * @param owner The player who owns the piece.
     * @param image A string representing the image or identifier of the piece.
     */
    public Piece(Player owner, String image) {
        this.owner = owner;
        this.image = image;
    }

    /**
     * Gets the image identifier of the piece.
     *
     * @return A string representing the image or identifier of the piece.
     */
    public String getImage() {
        return image;
    }

    /**
     * Gets the owner of the piece.
     *
     * @return The Player object representing the owner of the piece.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the square where the piece is placed.
     *
     * @param square The square to place the piece on.
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * Gets the current square where the piece is placed.
     *
     * @return The square where the piece is currently placed.
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Abstract method to determine if moving the piece to a given destination is a valid move.
     * This method must be implemented by each specific piece type.
     *
     * @param destination The square to which the piece is attempting to move.
     * @param board       The board on which the piece is placed.
     * @return true if the move is valid, false otherwise.
     */
    public abstract boolean validMove(Square destination, Board board);

    public abstract List<Square> getPossibleMoves(Board board);

}
