public class Pawn extends Piece {
    public Pawn(Player owner, String piece) {
        super(owner, "P");
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        return true;
    }
}

