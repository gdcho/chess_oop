/**
 * The Pawn class represents a pawn piece in a game of chess.
 * It extends the abstract Piece class and provides implementation
 * for the pawn-specific movement validation.
 */
public class Pawn extends Piece {

    /**
     * Constructs a Pawn object.
     *
     * @param owner The player who owns the piece.
     * @param piece The letter representation of the piece, typically "P" for pawns.
     */
    public Pawn(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Determines if the pawn can legally move to a specified destination square.
     *
     * @param destination The target square to move to.
     * @param board       The board on which the pawn is placed.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the pawn, including the level.
        int startLevel = this.getSquare().getLevel();
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position the pawn is trying to move to, including the level.
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Determine the direction of movement for the pawn based on its owner.
        int direction = this.getOwner().isWhite() ? -1 : 1;
        int levelDirection = destLevel - startLevel;

        // Pawns can only move up or down one level at a time, and cannot 'skip' levels.
        if (Math.abs(levelDirection) > 1) {
            return false;
        }

        // A pawn moves straight forward one square on the same level.
        if (levelDirection == 0 && startCol == destCol && destRow == startRow + direction && destination.getPiece() == null) {
            return true;
        }

        // A pawn moves straight forward one square, advancing to the next level.
        if (Math.abs(levelDirection) == 1 && startCol == destCol && destRow == startRow && destination.getPiece() == null) {
            return true;
        }

        // On its first move, a pawn can move two squares forward on the same level.
        if (levelDirection == 0 && startCol == destCol && startRow == (this.getOwner().isWhite() ? 6 : 1) &&
                destRow == startRow + 2 * direction && destination.getPiece() == null &&
                board.getSquareAt(startLevel, startRow + direction, startCol).getPiece() == null) {
            return true;
        }

        // Pawns capture diagonally, one square forward on the same level or advancing to the next level.
        if (Math.abs(startCol - destCol) == 1 && ((destRow == startRow + direction && levelDirection == 0) ||
                (destRow == startRow && Math.abs(levelDirection) == 1))) {
            if (destination.getPiece() != null && destination.getPiece().getOwner() != this.getOwner()) {
                return true;
            }
        }

        return false;
    }
}
