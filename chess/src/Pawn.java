public class Pawn extends Piece {
    public Pawn(Player owner, String piece) {
        super(owner, piece);
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();
        int destRow = destination.getRow();
        int destCol = destination.getCol();
        int direction = this.getOwner().isWhite() ? 1 : -1;

        if (startCol == destCol && destRow == startRow + direction && destination.getPiece() == null) {
            return true;
        }

        if (startCol == destCol && startRow == (this.getOwner().isWhite() ? 1 : 6) && destRow == startRow + 2 * direction) {
            if (board.getSquareAt(startRow + direction, startCol).getPiece() == null && destination.getPiece() == null) {
                return true;
            }
        }

        if (Math.abs(startCol - destCol) == 1 && destRow == startRow + direction) {
            if (destination.getPiece() != null && destination.getPiece().getOwner() != this.getOwner()) {
                return true;
            }
        }

        return false;
    }
}

