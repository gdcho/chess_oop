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

    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Check all squares in the same row
        for (int col = 0; col < 8; col++) {
            if (col != startCol && isPathClear(board, startLevel, startRow, startCol, startLevel, startRow, col)) {
                moves.add(board.getSquareAt(startLevel, startRow, col));
            }
        }

        // Check all squares in the same column
        for (int row = 0; row < 8; row++) {
            if (row != startRow && isPathClear(board, startLevel, startRow, startCol, startLevel, row, startCol)) {
                moves.add(board.getSquareAt(startLevel, row, startCol));
            }
        }

        // Check all squares in the same level
        for (int level = 0; level < 3; level++) {
            if (level != startLevel && isPathClear(board, startLevel, startRow, startCol, level, startRow, startCol)) {
                moves.add(board.getSquareAt(level, startRow, startCol));
            }
        }

        return moves;
    }

    private boolean isPathClear(Board board, int startLevel, int startRow, int startCol, int endLevel, int endRow, int endCol) {
        // Horizontal move
        if (startRow == endRow && startLevel == endLevel) {
            int minCol = Math.min(startCol, endCol);
            int maxCol = Math.max(startCol, endCol);
            for (int col = minCol + 1; col < maxCol; col++) {
                if (board.getSquareAt(startLevel, startRow, col).getPiece() != null) {
                    return false;
                }
            }
        }
        // Vertical move
        else if (startCol == endCol && startLevel == endLevel) {
            int minRow = Math.min(startRow, endRow);
            int maxRow = Math.max(startRow, endRow);
            for (int row = minRow + 1; row < maxRow; row++) {
                if (board.getSquareAt(startLevel, row, startCol).getPiece() != null) {
                    return false;
                }
            }
        }
        // Level move
        else if (startRow == endRow && startCol == endCol) {
            int minLevel = Math.min(startLevel, endLevel);
            int maxLevel = Math.max(startLevel, endLevel);
            for (int level = minLevel + 1; level < maxLevel; level++) {
                if (board.getSquareAt(level, startRow, startCol).getPiece() != null) {
                    return false;
                }
            }
        }

        return true;
    }

}