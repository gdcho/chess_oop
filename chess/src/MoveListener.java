import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveListener extends MouseAdapter {
    private Chess game;
    private Square startSquare;

    public MoveListener(Chess game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Square square = (Square) e.getSource();
        if (startSquare == null && square.getPiece() != null &&
                square.getPiece().getOwner() == game.getCurrentPlayer()) {
            startSquare = square;
            square.toggleHighlight();
        } else if (startSquare != null && startSquare != square) {
            if (startSquare.getPiece().validMove(square, game.getBoard())) {
                game.movePiece(startSquare, square);
                startSquare.toggleHighlight();
                startSquare = null;
            } else {
                startSquare.toggleHighlight();
                startSquare = null;
            }
        }
    }
}

