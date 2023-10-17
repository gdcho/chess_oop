public abstract class Piece {
    private String piece;
    private Player owner;
    private Square square;

    public Piece(Player owner, String piece) {
        this.owner = owner;
        this.piece = piece;
    }

    public String getPiece() {
        return piece;
    }

    public Player getOwner() {
        return owner;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public Square getSquare() {
        return square;
    }

    public abstract boolean validMove(Square destination, Board board);
}

