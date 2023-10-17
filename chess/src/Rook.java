public class Rook extends Piece {
    public Rook(Player owner, String r) {
        super(owner, "R");
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        return true;
    }
}
