/**
 * Represents the Knight piece in a game of chess.
 */
public class Knight extends Piece {

    /**
     * Constructs a Knight piece.
     *
     * @param owner the player who owns the piece
     * @param piece the textual representation of the piece
     */
    public Knight(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Checks if a move to a specified square is valid for the Knight piece.
     * The Knight moves in an 'L' shape: it can move two squares along a row and one along a column,
     * or two squares along a column and one along a row. This move can jump over other pieces.
     *
     * @param destination the square to move to
     * @param board       the board on which the move is to be made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the Knight.
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position the Knight is trying to move to.
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the row and column differences between the start and destination squares.
        int rowDiff = Math.abs(startRow - destRow);
        int colDiff = Math.abs(startCol - destCol);

        // Check for "L" shaped move.
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            // Move is valid if the destination square is empty or contains an opponent's piece.
            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }
}
