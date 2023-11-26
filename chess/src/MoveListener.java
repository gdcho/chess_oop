import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
        Square clickedSquare = (Square) e.getSource();

        if (startSquare != null) {
            if (startSquare.getPiece().validMove(clickedSquare, game.getBoard())) {
                game.move(startSquare, clickedSquare);
                game.getBoard().clearHighlights(); // Clear all highlights after the move
                startSquare = null;
            } else {
                game.getBoard().clearHighlights(); // Clear all highlights if the move is not valid
                startSquare = null;
            }
        }
        if (clickedSquare.getPiece() != null && clickedSquare.getPiece().getOwner() == game.getCurrentPlayer()) {
            // If a different piece is selected or no piece was previously selected
            if (startSquare != clickedSquare) {
                if (startSquare != null) {
                    highlightPossibleMoves(startSquare, false);
                }
                // Set the new start square and highlight its possible moves
                startSquare = clickedSquare;
                highlightPossibleMoves(startSquare, true);
            }
        }
    }

    /**
     * Highlights the possible moves for a piece on a square.
     *
     * @param square    the square containing the piece
     * @param highlight true if the possible moves should be highlighted, false otherwise
     */
    private void highlightPossibleMoves(Square square, boolean highlight) {
        for (int level = 0; level < 3; level++) {
            for (int row = 0; row < game.getBoard().getNumberOfRows(); row++) {
                for (int col = 0; col < game.getBoard().getNumberOfCols(); col++) {
                    Square s = game.getBoard().getSquareAt(level, row, col);
                    if (s.isActive()) {
                        s.setActive(false); // Deactivate the square
                    }
                }
            }
        }

        if (highlight) {
            List<Square> possibleMoves = square.getPiece().getPossibleMoves(game.getBoard());
            for (Square possibleMove : possibleMoves) {
                possibleMove.setActive(true); // Activate the possible move
            }
        }

        // Repaint the board to reflect the changes
        game.getBoard().repaint();
    }


}
