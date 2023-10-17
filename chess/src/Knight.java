public class Knight extends Piece {
    public Knight(Player owner, String n) {
        super(owner, "N");
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        return true;
    }
}
