/**
 * Represents a game board consisting of a grid of squares.
 */
public class Board {
    // The 2D array representing the squares on the board.
    private Square[][] squares;

    /**
     * Constructs a Board with a given MoveListener.
     *
     * @param listener the MoveListener that should be notified when a move is made
     */
    public Board(MoveListener listener) {
        // Initialize the 2D array to have 8 rows and 8 columns.
        squares = new Square[8][8];

        // Populate the array with Square objects and assign the listener to each.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j);
                squares[i][j].addMouseListener(listener);
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
    public Square getSquareAt(int row, int col) {
        return squares[row][col];
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
        return squares[0].length;
    }
}
