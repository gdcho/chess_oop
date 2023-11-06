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
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();
        int destRow = destination.getRow();
        int destCol = destination.getCol();
        int direction = this.getOwner().isWhite() ? -1 : 1;

        // A pawn moves straight forward one square.
        if (startCol == destCol && destRow == startRow + direction && destination.getPiece() == null) {
            return true;
        }

        // On its first move, a pawn can move two squares forward.
        if (startCol == destCol && startRow == (this.getOwner().isWhite() ? 6 : 1) &&
                destRow == startRow + 2 * direction) {
            if (board.getSquareAt(startRow + direction, startCol).getPiece() == null && destination.getPiece() == null) {
                return true;
            }
        }

        // Pawns capture diagonally, one square forward.
        if (Math.abs(startCol - destCol) == 1 && destRow == startRow + direction) {
            if (destination.getPiece() != null && destination.getPiece().getOwner() != this.getOwner()) {
                return true;
            }
        }

        return false;
    }
}
