/**
 * Represents a Queen piece on a chessboard, extending the Piece class.
 */
public class Queen extends Piece {

    /**
     * Constructs a new Queen piece with the specified owner and name.
     *
     * @param owner The player who owns the piece.
     * @param piece The name of the piece, typically a single character string.
     */
    public Queen(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Checks if moving the queen to the specified destination square is a valid move.
     *
     * @param destination The square to which the queen is being moved.
     * @param board       The chessboard on which the move is being made.
     * @return true if the move is valid, false otherwise.
     */
    public boolean validMove(Square destination, Board board) {
        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Check if the move is in a straight line (horizontally or vertically).
        boolean straightMove = (currentRow == destRow) || (currentCol == destCol);

        // Check if the move is diagonally.
        boolean diagonalMove = Math.abs(destRow - currentRow) == Math.abs(destCol - currentCol);

        if (straightMove) {
            return isPathClearStraight(currentRow, currentCol, destRow, destCol, board);
        }
        else if (diagonalMove) {
            return isPathClearDiagonal(currentRow, currentCol, destRow, destCol, board);
        }

        return false;
    }

    /**
     * Checks if the path is clear for a straight move (horizontally or vertically).
     *
     * @return true if no pieces are in the way, false otherwise.
     */
    private boolean isPathClearStraight(int startRow, int startCol, int endRow, int endCol, Board board) {
        // Horizontal move
        if (startRow == endRow) {
            int step = (startCol < endCol) ? 1 : -1;
            for (int col = startCol + step; col != endCol; col += step) {
                if (board.getSquareAt(startRow, col).getPiece() != null) {
                    return false;
                }
            }
        }
        // Vertical move
        else if (startCol == endCol) {
            int step = (startRow < endRow) ? 1 : -1;
            for (int row = startRow + step; row != endRow; row += step) {
                if (board.getSquareAt(row, startCol).getPiece() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the path is clear for a diagonal move.
     *
     * @return true if no pieces are in the way, false otherwise.
     */
    private boolean isPathClearDiagonal(int startRow, int startCol, int endRow, int endCol, Board board) {
        int rowStep = (startRow < endRow) ? 1 : -1;
        int colStep = (startCol < endCol) ? 1 : -1;

        int col = startCol + colStep;
        for (int row = startRow + rowStep; row != endRow; row += rowStep) {
            if (board.getSquareAt(row, col).getPiece() != null) {
                return false;
            }
            col += colStep;
        }
        return true;
    }

}
