import java.util.ArrayList;
import java.util.List;

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

        Piece destPiece = destination.getPiece();
        if (destPiece != null && destPiece.getOwner().equals(this.getOwner())) {
            return false; // Can't take pieces of the same color
        }

        // Check if the move is either horizontal, vertical, or across levels.
        if (start.getRow() == destination.getRow() && start.getCol() == destination.getCol()) {
            // Move across levels
            return isPathClearAcrossLevels(start, destination, board);
        }
        else if (start.getRow() == destination.getRow()) {
            // Horizontal move within the same level
            return isPathClearHorizontal(start, destination, board);
        }
        else if (start.getCol() == destination.getCol()) {
            // Vertical move within the same level
            return isPathClearVertical(start, destination, board);
        }

        return false;
    }

    /**
     * Checks if the level path between two squares is clear for a rook to move.
     *
     * @param start       The starting square.
     * @param destination The destination square.
     * @param board       The board on which the move is being made.
     * @return true if the path is clear, false otherwise.
     */
    private boolean isPathClearAcrossLevels(Square start, Square destination, Board board) {
        int startLevel = start.getLevel();
        int destLevel = destination.getLevel();
        int fixedRow = start.getRow();
        int fixedCol = start.getCol();
        int step = (startLevel < destLevel) ? 1 : -1;
        for (int level = startLevel + step; level != destLevel; level += step) {
            if (board.getSquareAt(level, fixedRow, fixedCol).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the horizontal path between two squares is clear for a rook to move.
     *
     * @param start       The starting square.
     * @param destination The destination square.
     * @param board       The board on which the move is being made.
     * @return true if the path is clear, false otherwise.
     */
    private boolean isPathClearHorizontal(Square start, Square destination, Board board) {
        int startCol = Math.min(start.getCol(), destination.getCol());
        int endCol = Math.max(start.getCol(), destination.getCol());
        int fixedRow = start.getRow();
        int fixedLevel = start.getLevel();
        for (int col = startCol + 1; col < endCol; col++) {
            if (board.getSquareAt(fixedLevel, fixedRow, col).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the vertical path between two squares is clear for a rook to move.
     *
     * @param start       The starting square.
     * @param destination The destination square.
     * @param board       The board on which the move is being made.
     * @return true if the path is clear, false otherwise.
     */
    private boolean isPathClearVertical(Square start, Square destination, Board board) {
        int startRow = Math.min(start.getRow(), destination.getRow());
        int endRow = Math.max(start.getRow(), destination.getRow());
        int fixedCol = start.getCol();
        int fixedLevel = start.getLevel();
        for (int row = startRow + 1; row < endRow; row++) {
            if (board.getSquareAt(fixedLevel, row, fixedCol).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of all possible moves for the rook piece.
     *
     * @param board the board on which the piece resides
     * @return a list of all possible moves for the rook piece
     */
    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Check for moves that change levels but keep the row and column constant
        for (int level = 0; level < 3; level++) {
            if (level != startLevel) {
                if (isPathClearAcrossLevels(board, startLevel, startRow, startCol, level, startRow, startCol)) {
                    moves.add(board.getSquareAt(level, startRow, startCol));
                }
            }
        }

        return moves;
    }

    /**
     * Checks if the path across all levels between two squares is clear for a rook to move.
     *
     * @param board       The board on which the move is being made.
     * @param startLevel  The starting level.
     * @param startRow    The starting row.
     * @param startCol    The starting column.
     * @param endLevel    The ending level.
     * @param endRow      The ending row.
     * @param endCol      The ending column.
     * @return true if the path is clear, false otherwise.
     */
    private boolean isPathClearAcrossLevels(Board board, int startLevel, int startRow, int startCol, int endLevel, int endRow, int endCol) {
        int minLevel = Math.min(startLevel, endLevel);
        int maxLevel = Math.max(startLevel, endLevel);
        for (int level = minLevel + 1; level < maxLevel; level++) {
            if (board.getSquareAt(level, startRow, startCol).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

}