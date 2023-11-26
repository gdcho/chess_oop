import java.util.List;
import java.util.ArrayList;

/**
 * Represents the Knight piece in a game of chess.
 */
public class Knight extends Piece {

    /**
     * Constructs a Knight piece.
     *
     * @param owner the player who owns the piece
     * @param piece the textual representation of the piece
     */
    public Knight(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Checks if a move to a specified square is valid for the Knight piece.
     * The Knight moves in an 'L' shape: it can move two squares along a row and one along a column,
     * or two squares along a column and one along a row. This move can jump over other pieces.
     *
     * @param destination the square to move to
     * @param board       the board on which the move is to be made
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the Knight, including the level.
        int startLevel = this.getSquare().getLevel();
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position the Knight is trying to move to, including the level.
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Calculate the differences in level, row, and column between the start and destination squares.
        int levelDiff = Math.abs(destLevel - startLevel);
        int rowDiff = Math.abs(startRow - destRow);
        int colDiff = Math.abs(startCol - destCol);

        Piece destPiece = destination.getPiece();
        if (destPiece != null && destPiece.getOwner().equals(this.getOwner())) {
            return false; // Can't take pieces of the same color
        }

        // Check for L shaped move in 3D space.
        boolean validLMove = (levelDiff == 0 && ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) ||
                (levelDiff == 1 && ((rowDiff == 2 && colDiff == 0) || (rowDiff == 0 && colDiff == 2) ||
                        (rowDiff == 1 && colDiff == 1)));

        // Move is valid if it's an L move and the destination square is empty or contains an opponent's piece.
        return validLMove && (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
    }

    /**
     * Gets a list of all possible moves for the Knight piece.
     *
     * @param board the board on which the piece resides
     * @return a list of all possible moves for the Knight piece
     */
    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();

        // Moves that involve changing levels
        int[][] levelChangeMovesOffset = {
                {-1, -1, -1}, {-1, -1, 1}, {-1, 1, -1}, {-1, 1, 1},
                {1, -1, -1}, {1, -1, 1}, {1, 1, -1}, {1, 1, 1}
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