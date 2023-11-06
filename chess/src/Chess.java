import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * The main class for a chess game that sets up the game board,
 * players, and initializes the GUI.
 */
public class Chess {

    private JFrame frame;
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    /**
     * Constructor for Chess class. Initializes players, board, and sets up the GUI.
     */
    public Chess() {
        // Initialize players with their respective colors.
        this.whitePlayer = new Player(java.awt.Color.RED);
        this.blackPlayer = new Player(java.awt.Color.BLUE);
        // Initialize the chess board with a move listener.
        this.board = new Board(new MoveListener(this));
        // Set the current player to white to start the game.
        this.currentPlayer = whitePlayer;
        // Initialize the game pieces.
        init();
        // Set up the graphical user interface.
        initGUI();
    }

    /**
     * Initializes the Graphical User Interface for the chess game.
     */
    private void initGUI() {
        frame = new JFrame("Chess OOP Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the chess board layout.
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        // Add all squares of the board to the panel.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardPanel.add(board.getSquareAt(i, j));
            }
        }
        frame.add(boardPanel);
        frame.setVisible(true);
    }

    /**
     * Initializes the game pieces on the board.
     */
    public void init() {
        initPieces();
    }

    /**
     * Places the pieces in their initial positions on the board.
     */
    private void initPieces() {
        setPiece(0, 0, new Rook(blackPlayer, "R"));
        setPiece(0, 1, new Bishop(blackPlayer, "B"));
        setPiece(0, 2, new Knight(blackPlayer, "N"));
        setPiece(0, 3, new King(blackPlayer, "K"));
        setPiece(0, 4, new Queen(blackPlayer, "Q"));
        setPiece(0, 5, new Knight(blackPlayer, "N"));
        setPiece(0, 6, new Bishop(blackPlayer, "B"));
        setPiece(0, 7, new Rook(blackPlayer, "R"));
        for (int col = 0; col < 8; col++) {
            setPiece(1, col, new Pawn(blackPlayer, "P"));
        }

        // Set pieces for black player
        setPiece(7, 0, new Rook(whitePlayer, "R"));
        setPiece(7, 1, new Bishop(whitePlayer, "B"));
        setPiece(7, 2, new Knight(whitePlayer, "N"));
        setPiece(7, 3, new King(whitePlayer, "K"));
        setPiece(7, 4, new Queen(whitePlayer, "Q"));
        setPiece(7, 5, new Knight(whitePlayer, "N"));
        setPiece(7, 6, new Bishop(whitePlayer, "B"));
        setPiece(7, 7, new Rook(whitePlayer, "R"));
        for (int col = 0; col < 8; col++) {
            setPiece(6, col, new Pawn(whitePlayer, "P"));
        }
    }

    /**
     * Sets a piece on a square and associates the square with the piece.
     *
     * @param row   the row of the square
     * @param col   the column of the square
     * @param piece the piece to set on the square
     */
    private void setPiece(int row, int col, Piece piece) {
        // Place the piece on the square at the given position.
        board.getSquareAt(row, col).setPiece(piece);
        // Set the piece's current square to the new position.
        piece.setSquare(board.getSquareAt(row, col));
    }

    /**
     * Returns the game board.
     *
     * @return the current game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the current player.
     *
     * @return the player whose turn it is
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Executes a move from one square to another.
     *
     * @param start the starting square of the move
     * @param end   the ending square of the move
     */
    public void move(Square start, Square end) {
        // Get the piece from the start square.
        Piece move = start.getPiece();
        // Move the piece to the end square.
        end.setPiece(move);
        // Clear the start square.
        start.setPiece(null);
        // Update the piece's position.
        move.setSquare(end);
        // Change turns between players.
        swapCurrentPlayer();
    }

    /**
     * Switches the current player from white to black, or black to white.
     */
    private void swapCurrentPlayer() {
        if (currentPlayer == whitePlayer) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

    /**
     * Main method that starts the chess game.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Chess chessGame = new Chess();
        // Additional initialization or methods could be called here if needed.
    }
}
