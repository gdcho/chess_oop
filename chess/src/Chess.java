import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Chess {

    private JFrame frame;
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    public Chess() {
        this.whitePlayer = new Player(java.awt.Color.RED);
        this.blackPlayer = new Player(java.awt.Color.BLUE);
        this.board = new Board(new MoveListener(this));
        this.currentPlayer = whitePlayer;
        init();
        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("Chess OOP Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardPanel.add(board.getSquareAt(i, j));
            }
        }
        frame.add(boardPanel);
        frame.setVisible(true);
    }
    public void init() {
        initPieces();
    }

    private void initPieces() {
        setPiece(0, 0, new Rook(whitePlayer, "R"));
        setPiece(0, 1, new Bishop(whitePlayer, "B"));
        setPiece(0, 2, new Knight(whitePlayer, "N"));
        setPiece(0, 3, new King(whitePlayer, "K"));
        setPiece(0, 4, new Queen(whitePlayer, "Q"));
        setPiece(0, 5, new Knight(whitePlayer, "N"));
        setPiece(0, 6, new Bishop(whitePlayer, "B"));
        setPiece(0, 7, new Rook(whitePlayer, "R"));
        for (int col = 0; col < 8; col++) {
            setPiece(1, col, new Pawn(whitePlayer, "P"));
        }

        // Set pieces for black player
        setPiece(7, 0, new Rook(blackPlayer, "R"));
        setPiece(7, 1, new Bishop(blackPlayer, "B"));
        setPiece(7, 2, new Knight(blackPlayer, "N"));
        setPiece(7, 3, new King(blackPlayer, "K"));
        setPiece(7, 4, new Queen(blackPlayer, "Q"));
        setPiece(7, 5, new Knight(blackPlayer, "N"));
        setPiece(7, 6, new Bishop(blackPlayer, "B"));
        setPiece(7, 7, new Rook(blackPlayer, "R"));
        for (int col = 0; col < 8; col++) {
            setPiece(6, col, new Pawn(blackPlayer, "P"));
        }
    }

    private void setPiece(int row, int col, Piece piece) {
        board.getSquareAt(row, col).setPiece(piece);
        piece.setSquare(board.getSquareAt(row, col));
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void movePiece(Square start, Square end) {
        Piece movedPiece = start.getPiece();
        end.setPiece(movedPiece);
        start.setPiece(null);
        movedPiece.setSquare(end);
        swapCurrentPlayer();
    }

    private void swapCurrentPlayer() {
        if (currentPlayer == whitePlayer) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

    // Additional methods from UML

    public static void main(String[] args) {
        Chess chessGame = new Chess();
        // Further initialization or methods
    }
}
