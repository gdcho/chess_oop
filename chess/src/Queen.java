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

        Piece destPiece = destination.getPiece();
        if (destPiece != null && destPiece.getOwner().equals(this.getOwner())) {
            return false; // Can't take pieces of the same color
        }

        // Check if the move is in a straight line
        boolean straightMove = (currentRow == destRow && currentCol == destCol) ||
                (currentRow == destRow && currentLevel == destLevel) ||
                (currentCol == destCol && currentLevel == destLevel);

        // Check if the move is diagonally in 2D or 3D
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
                if (!isValidSquare(level, startRow, startCol, board)) {
                    return false;
                }
            }
        }
        // Check for horizontal move
        else if (startRow == endRow && startLevel == endLevel) {
            int step = (startCol < endCol) ? 1 : -1;
            for (int col = startCol + step; col != endCol; col += step) {
                if (!isValidSquare(startLevel, startRow, col, board)) {
                    return false;
                }
            }
        }
        // Check for vertical move
        else if (startCol == endCol && startLevel == endLevel) {
            int step = (startRow < endRow) ? 1 : -1;
            for (int row = startRow + step; row != endRow; row += step) {
                if (!isValidSquare(startLevel, row, startCol, board)) {
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
            if (!isValidSquare(level, row, col, board)) {
                return false;
            }
            level += levelStep;
            row += rowStep;
            col += colStep;
        }

        return true;
    }

    /**
     * Checks if the specified square is a valid square on the board.
     *
     * @param level the level of the square
     * @param row   the row of the square
     * @param col   the column of the square
     * @param board the board on which the square resides
     * @return true if the square is valid, false otherwise
     */
    private boolean isValidSquare(int level, int row, int col, Board board) {
        return level >= 0 && level < 3 && row >= 0 && row < 8 && col >= 0 && col < 8 &&
                board.getSquareAt(level, row, col).getPiece() == null;
    }

    /**
     * Gets a list of all possible moves for the Queen piece.
     *
     * @param board the board on which the piece resides
     * @return a list of all possible moves for the Queen piece
     */
    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Check vertical, horizontal, and diagonal moves involving level changes
        for (int i = 0; i < 8; i++) {
            // Vertical moves with level change
            addMoveIfValid(moves, board, startLevel + 1, startRow, i);
            addMoveIfValid(moves, board, startLevel - 1, startRow, i);

            // Horizontal moves with level change
            addMoveIfValid(moves, board, startLevel + 1, i, startCol);
            addMoveIfValid(moves, board, startLevel - 1, i, startCol);

            // Diagonal moves with level change
            addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel + 1, startRow + i, startCol + i);
            addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel - 1, startRow + i, startCol + i);
            addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel + 1, startRow - i, startCol - i);
            addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel - 1, startRow - i, startCol - i);
        }

        return moves;
    }

    /**
     * Adds a move to the list of moves if the move is valid.
     *
     * @param moves the list of moves
     * @param board the board on which the piece resides
     * @param destLevel the level of the move
     * @param destRow the row of the move
     * @param destCol the column of the move
     * @return true if the move is valid, false otherwise
     */
    private void addMoveIfValid(List<Square> moves, Board board, int destLevel, int destRow, int destCol) {
        if (destRow >= 0 && destRow < 8 && destCol >= 0 && destCol < 8 && destLevel >= 0 && destLevel < 3) {
            Square possibleMove = board.getSquareAt(destLevel, destRow, destCol);
            if (validMove(possibleMove, board)) {
                moves.add(possibleMove);
            }
        }
    }

    /**
     * Adds a diagonal move to the list of moves if the move is valid.
     *
     * @param moves the list of moves
     * @param board the board on which the piece resides
     * @param startLevel the level of the start square
     * @param startRow the row of the start square
     * @param startCol the column of the start square
     * @param destLevel the level of the destination square
     * @param destRow the row of the destination square
     * @param destCol the column of the destination square
     * @return true if the move is valid, false otherwise
     */
    private void addDiagonalMoveIfValid(List<Square> moves, Board board, int startLevel, int startRow, int startCol, int destLevel, int destRow, int destCol) {
        if (destRow >= 0 && destRow < 8 && destCol >= 0 && destCol < 8 && destLevel >= 0 && destLevel < 3) {
            if (isPathClearDiagonal(startLevel, startRow, startCol, destLevel, destRow, destCol, board)) {
                moves.add(board.getSquareAt(destLevel, destRow, destCol));
            }
        }
    }

}
