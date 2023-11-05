public class King extends Piece {
    public King(Player owner, String piece) {
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

        // King moves one square in any direction
        if ((rowDiff == 1 && colDiff == 1) || // Diagonal
                (rowDiff == 1 && colDiff == 0) || // Vertical
                (rowDiff == 0 && colDiff == 1)) { // Horizontal
            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }
}
