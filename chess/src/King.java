import java.util.List;
import java.util.ArrayList;

/**
 * Represents the King piece in a game of chess.
 */
public class King extends Piece {

    /**
     * Constructs a King piece.
     *
     * @param owner the player who owns the piece
     * @param piece the textual representation of the piece
     */
    public King(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Checks if a move to a specified square is valid for the King piece.
     *
     * @param destination the square to move to
     * @param board       the board on which the move is to be made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the King
        int startLevel = this.getSquare().getLevel();
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position the King is trying to move to
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the differences in level, row, and column
        int levelDiff = Math.abs(destLevel - startLevel);
        int rowDiff = Math.abs(startRow - destRow);
        int colDiff = Math.abs(startCol - destCol);

        Piece destPiece = destination.getPiece();
        if (destPiece != null && destPiece.getOwner().equals(this.getOwner())) {
            return false; // Can't take pieces of the same color
        }

        // Check if the move is a single step in any direction
        if ((levelDiff == 1 && rowDiff == 0 && colDiff == 0) || // Up or down
                (levelDiff == 0 && rowDiff == 1 && colDiff == 0) || // Vertical
                (levelDiff == 0 && rowDiff == 0 && colDiff == 1) || // Horizontal
                (levelDiff == 0 && rowDiff == 1 && colDiff == 1) || // Diagonal on the same level
                (levelDiff == 1 && rowDiff == 1 && colDiff == 0) || // Diagonal across levels (vertical)
                (levelDiff == 1 && rowDiff == 0 && colDiff == 1) || // Diagonal across levels (horizontal)
                (levelDiff == 1 && rowDiff == 1 && colDiff == 1)) {  // Diagonal across levels (corner)
            // Move is valid if the destination square is empty or contains an opponent's piece.
            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }

    /**
     * Returns a list of all possible moves for the King piece.
     *
     * @param board the board on which the piece resides
     * @return a list of all possible moves for the King piece
     */
    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Moves that involve changing levels
        int[][] levelChangeMovesOffset = {
                {0, 0, 1}, {0, 0, -1}, // Up and down a level
                {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}, // Vertical and level
                {0, 1, 1}, {0, 1, -1}, {0, -1, 1}, {0, -1, -1}, // Horizontal and level
                {1, 1, 1}, {1, 1, -1}, {1, -1, 1}, {1, -1, -1}, // Diagonal and level
                {-1, 1, 1}, {-1, 1, -1}, {-1, -1, 1}, {-1, -1, -1} // Diagonal and level
        };

        for (int[] move : levelChangeMovesOffset) {
            int newRow = startRow + move[0];
            int newCol = startCol + move[1];
            int newLevel = startLevel + move[2];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && newLevel >= 0 && newLevel < 3) {
                Square possibleMove = board.getSquareAt(newLevel, newRow, newCol);
                if (validMove(possibleMove, board)) {
                    moves.add(possibleMove);
                }
            }
        }

        return moves;
    }

}
