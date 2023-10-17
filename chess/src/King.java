public class King extends Piece {
    public King(Player owner, String k) {
        super(owner, "K");
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        return true;
    }
}
