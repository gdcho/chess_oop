public class Queen extends Piece {

    public Queen(Player owner, String piece) {
        super(owner, piece);
    }

    public boolean validMove(Square destination, Board board) {
        int currentRow = this.getSquare().getRow();
        int currentCol = this.getSquare().getCol();
        int destRow = destination.getRow();
        int destCol = destination.getCol();

        boolean straightMove = (currentRow == destRow) || (currentCol == destCol);

        boolean diagonalMove = Math.abs(destRow - currentRow) == Math.abs(destCol - currentCol);

        if (straightMove) {
            return isPathClearStraight(currentRow, currentCol, destRow, destCol, board);
        } else if (diagonalMove) {
            return isPathClearDiagonal(currentRow, currentCol, destRow, destCol, board);
        }

        return false;
    }

    private boolean isPathClearStraight(int startRow, int startCol, int endRow, int endCol, Board board) {
        if (startRow == endRow) {
            int step = (startCol < endCol) ? 1 : -1;
            for (int col = startCol + step; col != endCol; col += step) {
                if (board.getSquareAt(startRow, col).getPiece() != null) {
                    return false;
                }
            }
        } else if (startCol == endCol) {
            int step = (startRow < endRow) ? 1 : -1;
            for (int row = startRow + step; row != endRow; row += step) {
                if (board.getSquareAt(row, startCol).getPiece() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPathClearDiagonal(int startRow, int startCol, int endRow, int endCol, Board board) {
        int rowStep = (startRow < endRow) ? 1 : -1;
        int colStep = (startCol < endCol) ? 1 : -1;

        int col = startCol + colStep;
        for (int row = startRow + rowStep; row != endRow; row += rowStep) {
            if (board.getSquareAt(row, col).getPiece() != null) {
                return false;
            }
            col += colStep;
        }
        return true;
    }

}
