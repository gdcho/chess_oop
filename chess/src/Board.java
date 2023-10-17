public class Board {
    private Square[][] squares;

    public Board(MoveListener listener) {
        // 8x8 board
        squares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j);
                squares[i][j].addMouseListener(listener);
            }
        }
    }

    public Square getSquareAt(int row, int col) {
        return squares[row][col];
    }

    public int getNumberOfRows() {
        return squares.length;
    }

    public int getNumberOfCols() {
        return squares[0].length;
    }
}
