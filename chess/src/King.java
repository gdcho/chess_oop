/**
 * Represents the King piece in a game of chess.
 */
public class King extends Piece {

    /**
     * Constructs a King piece.
     *
     * @param owner the player who owns the piece
     * @param piece the textual representation of the piece
     */
    public King(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Checks if a move to a specified square is valid for the King piece.
     * The King can move exactly one square horizontally, vertically, or diagonally.
     *
     * @param destination the square to move to
     * @param board       the board on which the move is to be made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the King.
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position the King is trying to move to.
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the row and column differences between the start and destination.
        int rowDiff = Math.abs(startRow - destRow);
        int colDiff = Math.abs(startCol - destCol);

        // Check if the move is either a single step move in any direction.
        if ((rowDiff == 1 && colDiff == 1) || // Diagonal move
                (rowDiff == 1 && colDiff == 0) || // Vertical move
                (rowDiff == 0 && colDiff == 1)) { // Horizontal move
            // Move is valid if the destination square is empty or contains an opponent's piece.
            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }
}
