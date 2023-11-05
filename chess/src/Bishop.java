public class Bishop extends Piece {
    public Bishop(Player owner, String piece) {
        super(owner, piece);
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        int rowDiff = Math.abs(startRow - destRow);
        int colDiff = Math.abs(startCol - destCol);

        // Check for diagonal move
        if (rowDiff == colDiff) {
            int rowDirection = (destRow - startRow) / rowDiff;
            int colDirection = (destCol - startCol) / colDiff;

            for (int i = 1; i < rowDiff; i++) {
                if (board.getSquareAt(startRow + i * rowDirection, startCol + i * colDirection).getPiece() != null) {
                    return false; // Path is blocked
                }
            }

            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }
}
