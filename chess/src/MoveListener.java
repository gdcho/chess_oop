import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A mouse listener that handles mouse clicks on squares of the chess board.
 * It determines the start and end of a move based on player interaction.
 */
public class MoveListener extends MouseAdapter {
    private Chess game;
    private Square startSquare;

    /**
     * Constructs a MoveListener.
     *
     * @param game the instance of the Chess game that this listener is a part of
     */
    public MoveListener(Chess game) {
        this.game = game;
    }

    /**
     * Handles mouse click events on the chess board squares.
     *
     * @param e the MouseEvent object that contains details about the mouse click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // Cast the event source to a Square object.
        Square square = (Square) e.getSource();

        if (startSquare == null && square.getPiece() != null &&
                square.getPiece().getOwner() == game.getCurrentPlayer()) {
            // Select the piece to move.
            startSquare = square;
            // Highlight the square to indicate selection.
            square.toggleHighlight();
        }
        else if (startSquare != null && startSquare != square) {
            // Check if the move is valid.
            if (startSquare.getPiece().validMove(square, game.getBoard())) {
                game.move(startSquare, square);
                // Unhighlight the starting square.
                startSquare.toggleHighlight();
                // Reset the start square for the next move.
                startSquare = null;
            } else {
                startSquare.toggleHighlight();
                startSquare = null;
            }
        }
    }
}
