import java.util.List;
import java.util.ArrayList;

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
    /**
     * Checks if the move to the destination square is valid for a bishop in a 3D space.
     *
     * @param destination the Square3D to move to
     * @param board the current 3D board on which the piece resides
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the piece.
        int startLevel = this.getSquare().getLevel();
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position to move to.
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the differences in level, rows, and columns.
        int levelDiff = Math.abs(destLevel - startLevel);
        int rowDiff = Math.abs(destRow - startRow);
        int colDiff = Math.abs(destCol - startCol);

        // Check if the move is a 2D diagonal on the same level or a 3D diagonal move.
        if ((levelDiff == 0 && rowDiff == colDiff && rowDiff > 0) || (levelDiff == rowDiff && rowDiff == colDiff && levelDiff > 0)) {
            return isPathClear(startLevel, startRow, startCol, destLevel, destRow, destCol, board);
        }

        return false;
    }

    private boolean isPathClear(int startLevel, int startRow, int startCol, int destLevel, int destRow, int destCol, Board board) {
        int levelDiff = Math.abs(destLevel - startLevel);
        int rowDiff = Math.abs(destRow - startRow);
        int colDiff = Math.abs(destCol - startCol);

        int levelDirection = Integer.compare(destLevel, startLevel);
        int rowDirection = Integer.compare(destRow, startRow);
        int colDirection = Integer.compare(destCol, startCol);

        // Use the maximum difference as the number of steps to check in the path
        int steps = Math.max(Math.max(levelDiff, rowDiff), colDiff);

        // Check each square along the path for a piece
        for (int i = 1; i < steps; i++) {
            int currentLevel = startLevel + i * levelDirection;
            int currentRow = startRow + i * rowDirection;
            int currentCol = startCol + i * colDirection;

            // If any square along the path is not empty, the path is not clear
            if (board.getSquareAt(currentLevel, currentRow, currentCol).getPiece() != null) {
                return false;
            }
        }

        // The path is clear if no pieces are found in all the squares along the path
        return true;
    }

    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Check all diagonal moves on the same level and diagonally across levels
        for (int i = -7; i <= 7; i++) {
            if (i != 0) {
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel, startRow + i, startCol + i);
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel, startRow - i, startCol + i);
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel + i, startRow + i, startCol);
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel - i, startRow + i, startCol);
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel + i, startRow, startCol + i);
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel - i, startRow, startCol + i);
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel + i, startRow + i, startCol + i);
                addDiagonalMoveIfValid(moves, board, startLevel, startRow, startCol, startLevel + i, startRow - i, startCol - i);
            }
        }

        return moves;
    }

    private void addDiagonalMoveIfValid(List<Square> moves, Board board, int startLevel, int startRow, int startCol, int destLevel, int destRow, int destCol) {
        if (destRow >= 0 && destRow < 8 && destCol >= 0 && destCol < 8 && destLevel >= 0 && destLevel < 3) {
            if (isPathClear(startLevel, startRow, startCol, destLevel, destRow, destCol, board)) {
                moves.add(board.getSquareAt(destLevel, destRow, destCol));
            }
        }
    }

}