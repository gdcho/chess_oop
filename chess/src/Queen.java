public class Queen extends Piece {
    public Queen(Player owner, String q) {
        super(owner, "Q");
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        return true;
    }
}
