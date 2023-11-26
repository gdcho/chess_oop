import java.util.List;
import java.util.ArrayList;

/**
 * The Pawn class represents a pawn piece in a game of chess.
 * It extends the abstract Piece class and provides implementation
 * for the pawn-specific movement validation.
 */
public class Pawn extends Piece {

    /**
     * Constructs a Pawn object.
     *
     * @param owner The player who owns the piece.
     * @param piece The letter representation of the piece, typically "P" for pawns.
     */
    public Pawn(Player owner, String piece) {
        super(owner, piece);
    }

    /**
     * Determines if the pawn can legally move to a specified destination square.
     *
     * @param destination The target square to move to.
     * @param board       The board on which the pawn is placed.
     * @return true if the move is valid, false otherwise.
     */
    @Override
    public boolean validMove(Square destination, Board board) {
        // Get the current position of the pawn, including the level.
        int startLevel = this.getSquare().getLevel();
        int startRow = this.getSquare().getRow();
        int startCol = this.getSquare().getCol();

        // Get the position the pawn is trying to move to, including the level.
        int destLevel = destination.getLevel();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        // Determine the direction of movement for the pawn based on its owner.
        int direction = this.getOwner().isWhite() ? -1 : 1;
        int levelDirection = destLevel - startLevel;

        Piece destPiece = destination.getPiece();
        if (destPiece != null && destPiece.getOwner().equals(this.getOwner())) {
            return false; // Can't take pieces of the same color
        }

        // Pawns can only move up or down one level at a time, and cannot 'skip' levels.
        if (Math.abs(levelDirection) > 1) {
            return false;
        }

        // Max distance of 2 if moving between boards.
        if (Math.abs(levelDirection) == 1 && (startLevel != 0 && startLevel != 2)) {
            return false;
        }

        // A pawn moves straight forward one square on the same level.
        if (levelDirection == 0 && startCol == destCol && destRow == startRow + direction && destination.getPiece() == null) {
            return true;
        }

        // A pawn moves straight forward one square, advancing to the next level.
        if (Math.abs(levelDirection) == 1 && startCol == destCol && destRow == startRow && destination.getPiece() == null) {
            return true;
        }

        // On its first move, a pawn can move two squares forward on the same level.
        if (levelDirection == 0 && startCol == destCol && isFirstMove(startRow) &&
                destRow == startRow + 2 * direction && destination.getPiece() == null &&
                board.getSquareAt(startLevel, startRow + direction, startCol).getPiece() == null) {
            return true;
        }

        // On its first move, a pawn can move to the top board in the same column.
        if (isFirstMove(startRow) && destLevel == (this.getOwner().isWhite() ? 2 : 0) && destCol == startCol &&
                destRow == (this.getOwner().isWhite() ? 4 : 3) && destination.getPiece() == null) {
            return true;
        }

        // Pawns capture diagonally, one square forward on the same level or advancing to the next level.
        if (Math.abs(startCol - destCol) == 1 && ((destRow == startRow + direction && levelDirection == 0) ||
                (destRow == startRow && Math.abs(levelDirection) == 1))) {
            if (destination.getPiece() != null && destination.getPiece().getOwner() != this.getOwner()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if this is the pawn's first move based on its starting row.
     *
     * @param startRow the starting row of the pawn
     * @return true if this is the pawn's first move, false otherwise
     */
    private boolean isFirstMove(int startRow) {
        return (this.getOwner().isWhite() && startRow == 6) || (!this.getOwner().isWhite() && startRow == 1);
    }

    /**
     * Returns a list of all possible moves for the pawn piece.
     *
     * @param board the board on which the piece resides
     * @return a list of all possible moves for the pawn piece
     */
    @Override
    public List<Square> getPossibleMoves(Board board) {
        List<Square> moves = new ArrayList<>();
        int startLevel = getSquare().getLevel();
        int startRow = getSquare().getRow();
        int startCol = getSquare().getCol();
        int direction = this.getOwner().isWhite() ? -1 : 1;

        // Check for moving straight to the top board on the first move
        if (isFirstMove(startRow)) {
            int newLevel = this.getOwner().isWhite() ? 2 : 0;
            int newRow = this.getOwner().isWhite() ? 4 : 3;
            addMoveIfValid(moves, board, newLevel, newRow, startCol);
        }

        // Check for diagonal captures that involve changing levels
        for (int newCol : new int[]{startCol - 1, startCol + 1}) {
            if (startLevel == 0 || startLevel == 2) { // Allow diagonal capture on moving up or down a level
                int newLevel = startLevel == 0 ? 1 : 1;
                addCaptureIfValid(moves, board, newLevel, startRow, newCol);
            }
        }

        return moves;
    }

    /**
     * Adds a move to the list of moves if the move is valid.
     *
     * @param moves the list of moves
     * @param board the board on which the piece resides
     * @param level the level of the move
     * @param row the row of the move
     * @param col the column of the move
     * @return true if the move is valid, false otherwise
     */
    private void addMoveIfValid(List<Square> moves, Board board, int level, int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8 && level >= 0 && level < 3) {
            Square possibleMove = board.getSquareAt(level, row, col);
            if (possibleMove.getPiece() == null) {
                moves.add(possibleMove);
            }
        }
    }

    /**
     * Adds a capture to the list of moves if the capture is valid.
     *
     * @param moves the list of moves
     * @param board the board on which the piece resides
     * @param level the level of the capture
     * @param row the row of the capture
     * @param col the column of the capture
     * @return true if the capture is valid, false otherwise
     */
    private void addCaptureIfValid(List<Square> moves, Board board, int level, int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8 && level >= 0 && level < 3) {
            Square possibleMove = board.getSquareAt(level, row, col);
            if (possibleMove.getPiece() != null && possibleMove.getPiece().getOwner() != this.getOwner()) {
                moves.add(possibleMove);
            }
        }
    }

}
