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
        // Get the current position of the piece
        int startLevel = this.getSquare().getLevel();
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position to move to
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the differences in level, rows, and columns
        int levelDiff = Math.abs(destLevel - startLevel);
        int rowDiff = Math.abs(destRow - startRow);
        int colDiff = Math.abs(destCol - startCol);

        Piece destPiece = destination.getPiece();
        if (destPiece != null && destPiece.getOwner().equals(this.getOwner())) {
            return false; // Can't take pieces of the same color
        }

        // Check if the move is a 2D diagonal on the same level or a 3D diagonal move
        if ((levelDiff == 0 && rowDiff == colDiff && rowDiff > 0) || (levelDiff == rowDiff && rowDiff == colDiff && levelDiff > 0)) {
            return isPathClear(startLevel, startRow, startCol, destLevel, destRow, destCol, board);
        }

        return false;
    }

    /**
     * Checks if the path is clear for a diagonal move.
     * @param startLevel the starting level
     * @param startRow the starting row
     * @param startCol the starting column
     * @param destLevel the destination level
     * @param destRow the destination row
     * @param destCol the destination column
     * @param board the current board on which the piece resides
     * @return true if no pieces are in the way, false otherwise.
     */
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

        return true;
    }

    /**
     * Returns a list of all possible moves for a bishop in a 3D space.
     *
     * @param board the current 3D board on which the piece resides
     * @return a list of all possible moves for a bishop in a 3D space
     */
    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Iterate through all possible diagonal moves
        for (int levelOffset = -2; levelOffset <= 2; levelOffset++) {
            for (int rowOffset = -7; rowOffset <= 7; rowOffset++) {
                for (int colOffset = -7; colOffset <= 7; colOffset++) {
                    if (Math.abs(levelOffset) == Math.abs(rowOffset) && Math.abs(rowOffset) == Math.abs(colOffset)) {
                        int destLevel = startLevel + levelOffset;
                        int destRow = startRow + rowOffset;
                        int destCol = startCol + colOffset;

                        if (isValidMove(destLevel, destRow, destCol, board)) {
                            moves.add(board.getSquareAt(destLevel, destRow, destCol));
                        }
                    }
                }
            }
        }

        return moves;
    }

    /**
     * Checks if the move to the destination square is valid for a bishop in a 3D space.
     *
     * @param destLevel the destination level
     * @param destRow the destination row
     * @param destCol the destination column
     * @param board the current 3D board on which the piece resides
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidMove(int destLevel, int destRow, int destCol, Board board) {
        if (destLevel >= 0 && destLevel < 3 && destRow >= 0 && destRow < 8 && destCol >= 0 && destCol < 8) {
            return isPathClear(this.getSquare().getLevel(), this.getSquare().getRow(), this.getSquare().getCol(),
                    destLevel, destRow, destCol, board);
        }
        return false;
    }

}