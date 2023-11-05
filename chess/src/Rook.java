public class Rook extends Piece {
    public Rook(Player owner, String piece) {
        super(owner, piece);
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        Square start = this.getSquare();
        if (start.getRow() == destination.getRow() || start.getCol() == destination.getCol()) {
            if (start.getRow() == destination.getRow()) {
                int fixedRow = start.getRow();
                int startCol = Math.min(start.getCol(), destination.getCol());
                int endCol = Math.max(start.getCol(), destination.getCol());
                for (int col = startCol + 1; col < endCol; col++) {
                    if (board.getSquareAt(fixedRow, col).getPiece() != null) {
                        return false;
                    }
                }
            } else {
                int fixedCol = start.getCol();
                int startRow = Math.min(start.getRow(), destination.getRow());
                int endRow = Math.max(start.getRow(), destination.getRow());
                for (int row = startRow + 1; row < endRow; row++) {
                    if (board.getSquareAt(row, fixedCol).getPiece() != null) {
                        return false;
                    }
                }
            }
            if (destination.getPiece() == null ||
                    !destination.getPiece().getOwner().equals(this.getOwner())) {
                return true;
            }
        }
        return false;
    }
}
