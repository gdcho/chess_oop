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
     * The King can move exactly one square horizontally, vertically, or diagonally.
     *
     * @param destination the square to move to
     * @param board       the board on which the move is to be made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the King, including the level.
        int startLevel = this.getSquare().getLevel();
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position the King is trying to move to, including the level.
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the differences in level, row, and column between the start and destination.
        int levelDiff = Math.abs(destLevel - startLevel);
        int rowDiff = Math.abs(startRow - destRow);
        int colDiff = Math.abs(startCol - destCol);

        // Check if the move is a single step in any direction, including level changes.
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

    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Offsets for all possible moves of a King
        int[][] movesOffset = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0}, // Horizontal and vertical
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, // Diagonal on the same level
                {0, 0, 1}, {0, 0, -1}, // Up and down a level
                {1, 0, 1}, {1, 0, -1}, {-1, 0, 1}, {-1, 0, -1}, // Vertical and level
                {0, 1, 1}, {0, 1, -1}, {0, -1, 1}, {0, -1, -1}, // Horizontal and level
                {1, 1, 1}, {1, 1, -1}, {1, -1, 1}, {1, -1, -1}, // Diagonal and level
                {-1, 1, 1}, {-1, 1, -1}, {-1, -1, 1}, {-1, -1, -1} // Diagonal and level
        };

        for (int[] move : movesOffset) {
            int newRow = startRow + move[0];
            int newCol = startCol + (move.length > 1 ? move[1] : 0);
            int newLevel = startLevel + (move.length > 2 ? move[2] : 0);

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && newLevel >= 0 && newLevel < 3) {
                Square possibleMove = board.getSquareAt(newLevel, newRow, newCol);
                if (possibleMove.getPiece() == null || possibleMove.getPiece().getOwner() != this.getOwner()) {
                    moves.add(possibleMove);
                }
            }
        }

        return moves;
    }

}
