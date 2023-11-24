import java.util.ArrayList;
import java.util.List;

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
        int currentLevel = this.getSquare().getLevel();
        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Check if the move is in a straight line (horizontally, vertically, or across levels).
        boolean straightMove = (currentRow == destRow && currentCol == destCol) ||
                (currentRow == destRow && currentLevel == destLevel) ||
                (currentCol == destCol && currentLevel == destLevel);

        // Check if the move is diagonally in 2D or 3D (including across levels).
        boolean diagonalMove = Math.abs(destRow - currentRow) == Math.abs(destCol - currentCol) && currentLevel == destLevel ||
                Math.abs(destRow - currentRow) == Math.abs(destLevel - currentLevel) && currentCol == destCol ||
                Math.abs(destCol - currentCol) == Math.abs(destLevel - currentLevel) && currentRow == destRow ||
                Math.abs(destRow - currentRow) == Math.abs(destCol - currentCol) && Math.abs(destRow - currentRow) == Math.abs(destLevel - currentLevel);

        if (straightMove) {
            return isPathClearStraight(currentLevel, currentRow, currentCol, destLevel, destRow, destCol, board);
        }
        else if (diagonalMove) {
            return isPathClearDiagonal(currentLevel, currentRow, currentCol, destLevel, destRow, destCol, board);
        }

        return false;
    }

    /**
     * Checks if the path is clear for a straight move (horizontally or vertically).
     *
     * @return true if no pieces are in the way, false otherwise.
     */
    private boolean isPathClearStraight(int startLevel, int startRow, int startCol, int endLevel, int endRow, int endCol, Board board) {
        // Check for level move
        if (startRow == endRow && startCol == endCol) {
            int step = (startLevel < endLevel) ? 1 : -1;
            for (int level = startLevel + step; level != endLevel; level += step) {
                if (board.getSquareAt(level, startRow, startCol).getPiece() != null) {
                    return false;
                }
            }
        }
        // Check for horizontal move
        else if (startRow == endRow && startLevel == endLevel) {
            int step = (startCol < endCol) ? 1 : -1;
            for (int col = startCol + step; col != endCol; col += step) {
                if (board.getSquareAt(startLevel, startRow, col).getPiece() != null) {
                    return false;
                }
            }
        }
        // Check for vertical move
        else if (startCol == endCol && startLevel == endLevel) {
            int step = (startRow < endRow) ? 1 : -1;
            for (int row = startRow + step; row != endRow; row += step) {
                if (board.getSquareAt(startLevel, row, startCol).getPiece() != null) {
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
    private boolean isPathClearDiagonal(int startLevel, int startRow, int startCol, int endLevel, int endRow, int endCol, Board board) {
        int levelStep = Integer.compare(endLevel, startLevel);
        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);

        int level = startLevel + levelStep;
        int row = startRow + rowStep;
        int col = startCol + colStep;

        while (level != endLevel || row != endRow || col != endCol) {
            if (board.getSquareAt(level, row, col).getPiece() != null) {
                return false;
            }
            level += levelStep;
            row += rowStep;
            col += colStep;
        }

        return true;
    }

    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Horizontal and vertical moves (like a Rook)
        for (int i = 0; i < 8; i++) {
            if (i != startCol) {
                if (isPathClearStraight(startLevel, startRow, startCol, startLevel, startRow, i, board)) {
                    moves.add(board.getSquareAt(startLevel, startRow, i));
                }
            }
            if (i != startRow) {
                if (isPathClearStraight(startLevel, startRow, startCol, startLevel, i, startCol, board)) {
                    moves.add(board.getSquareAt(startLevel, i, startCol));
                }
            }
            if (i != startLevel) {
                if (isPathClearStraight(startLevel, startRow, startCol, i, startRow, startCol, board)) {
                    moves.add(board.getSquareAt(i, startRow, startCol));
                }
            }
        }

        // Diagonal moves (like a Bishop)
        for (int i = -7; i <= 7; i++) {
            if (i != 0) {
                int newRow = startRow + i;
                int newCol = startCol + i;
                int newLevel = startLevel + i;

                // Diagonal in same level
                if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                    if (isPathClearDiagonal(startLevel, startRow, startCol, startLevel, newRow, newCol, board)) {
                        moves.add(board.getSquareAt(startLevel, newRow, newCol));
                    }
                }

                // Diagonal across levels (vertical)
                if (newRow >= 0 && newRow < 8 && newLevel >= 0 && newLevel < 3) {
                    if (isPathClearDiagonal(startLevel, startRow, startCol, newLevel, newRow, startCol, board)) {
                        moves.add(board.getSquareAt(newLevel, newRow, startCol));
                    }
                }

                // Diagonal across levels (horizontal)
                if (newCol >= 0 && newCol < 8 && newLevel >= 0 && newLevel < 3) {
                    if (isPathClearDiagonal(startLevel, startRow, startCol, newLevel, startRow, newCol, board)) {
                        moves.add(board.getSquareAt(newLevel, startRow, newCol));
                    }
                }

                // Diagonal in 3D
                if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && newLevel >= 0 && newLevel < 3) {
                    if (isPathClearDiagonal(startLevel, startRow, startCol, newLevel, newRow, newCol, board)) {
                        moves.add(board.getSquareAt(newLevel, newRow, newCol));
                    }
                }
            }
        }

        return moves;
    }

}
