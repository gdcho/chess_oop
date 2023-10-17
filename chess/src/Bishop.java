public class Bishop extends Piece {
    public Bishop(Player owner, String b) {
        super(owner, "B");
    }

    @Override
    public boolean validMove(Square destination, Board board) {
        return true;
    }
}
