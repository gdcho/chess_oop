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
        int colDiff = Math.abs(startCol - destCol);

        // Check if the move is along a 3D diagonal by comparing level, row, and column differences.
        if (levelDiff == rowDiff && rowDiff == colDiff) {
            int levelDirection = (destLevel - startLevel) / levelDiff;
            int rowDirection = (destRow - startRow) / rowDiff;
            int colDirection = (destCol - startCol) / colDiff;

            for (int i = 1; i < levelDiff; i++) {
                if (board.getSquareAt(startLevel + i * levelDirection, startRow + i * rowDirection, startCol + i * colDirection).getPiece() != null) {
                    return false;
                }
            }

            // The move is valid if the destination is empty or contains an opponent's piece.
            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }
}
