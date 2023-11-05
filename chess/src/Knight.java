public class Knight extends Piece {
    public Knight(Player owner, String piece) {
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

        // Check for "L" shaped move
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            return (destination.getPiece() == null || destination.getPiece().getOwner() != this.getOwner());
        }

        return false;
    }
}
