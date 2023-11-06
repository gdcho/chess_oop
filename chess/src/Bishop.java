/**
 * Represents a Bishop chess piece, which moves diagonally on the board.
 */
public class Bishop extends Piece {

    /**
     * Constructs a Bishop with the specified owner and piece type.
     *
     * @param owner the player that owns this piece
     * @param piece the type or name of the piece
     */
    public Bishop(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Checks if the move to the destination square is valid for a bishop.
     *
     * @param destination the Square to move to
     * @param board the current board on which the piece resides
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the piece.
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();
        // Get the position to move to.
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the difference in rows and columns.
        int rowDiff = Math.abs(startRow - destRow);
        int colDiff = Math.abs(startCol - destCol);

        // Check if the move is along a diagonal by comparing row and column differences.
        if (rowDiff == colDiff) {
            int rowDirection = (destRow - startRow) / rowDiff;
            int colDirection = (destCol - startCol) / colDiff;

            for (int i = 1; i < rowDiff; i++) {
                if (board.getSquareAt(startRow + i * rowDirection, startCol + i * colDirection).getPiece() != null) {
                    return false;
                }
            }

            // The move is valid if the destination is empty or contains an opponent's piece.
            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }
}
