/**
 * Represents a Rook piece on a chessboard, extending the Piece class.
 */
public class Rook extends Piece {

    /**
     * Constructs a new Rook piece with the specified owner and name.
     *
     * @param owner The player who owns the piece.
     * @param piece The name of the piece, typically a single character string.
     */
    public Rook(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Checks if moving the rook to the specified destination square is a valid move.
     *
     * @param destination The square to which the rook is being moved.
     * @param board       The chessboard on which the move is being made.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        Square start = this.getSquare();

        // Rooks move either in a horizontal or vertical line.
        if (start.getRow() == destination.getRow() || start.getCol() == destination.getCol()) {
            if (start.getRow() == destination.getRow()) {
                int fixedRow = start.getRow();
                int startCol = Math.min(start.getCol(), destination.getCol());
                int endCol = Math.max(start.getCol(), destination.getCol());
                for (int col = startCol + 1; col < endCol; col++) {
                    if (board.getSquareAt(fixedRow, col).getPiece() != null) {
                        return false;
                    }
                }
            }
            else {
                int fixedCol = start.getCol();
                int startRow = Math.min(start.getRow(), destination.getRow());
                int endRow = Math.max(start.getRow(), destination.getRow());
                for (int row = startRow + 1; row < endRow; row++) {
                    if (board.getSquareAt(row, fixedCol).getPiece() != null) {
                        return false;
                    }
                }
            }
            // Move is valid if the destination square is either empty or contains an opponent's piece.
            return destination.getPiece() == null ||
                    !destination.getPiece().getOwner().equals(this.getOwner());
        }
        // Move is not valid if it's not strictly horizontal or vertical.
        return false;
    }
}
