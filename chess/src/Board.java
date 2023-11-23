/**
 * Represents a game board consisting of a grid of squares.
 */
public class Board {
    // The 2D array representing the squares on the board.
    private Square[][][] squares;

    /**
     * Constructs a Board with a given MoveListener.
     *
     * @param listener the MoveListener that should be notified when a move is made
     */
    public Board(MoveListener listener) {
        // Initialize a 3D array for 3 levels of 8x8 boards.
        squares = new Square[3][8][8];

        for (int level = 0; level < 3; level++) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    squares[level][row][col] = new Square(level, row, col);
                    squares[level][row][col].addMouseListener(listener);
                }
            }
        }
    }

    /**
     * Retrieves the square at the specified row and column.
     *
     * @param row the row index of the square
     * @param col the column index of the square
     * @return the Square at the specified location
     */
    public Square getSquareAt(int level, int row, int col) {
        if (level >= 0 && level < 3 && row >= 0 && row < 8 && col >= 0 && col < 8) {
            return squares[level][row][col];
        }
        return null;
    }

    /**
     * Gets the number of rows on the board.
     *
     * @return the number of rows
     */
    public int getNumberOfRows() {
        return squares.length;
    }

    /**
     * Gets the number of columns on the board.
     *
     * @return the number of columns
     */
    public int getNumberOfCols() {
        return squares[0][0].length; }

}
