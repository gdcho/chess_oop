import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

/**
 * Represents a single square on a chessboard, which can hold a piece.
 */
public class Square extends JPanel {
    // Constants for the default colors of the squares and the highlight color.
    private static final Color LIGHT_COLOR = Color.WHITE;
    private static final Color DARK_COLOR = Color.BLACK;
    private static final Color HIGHLIGHT_COLOR = Color.RED;

    // Position of the square on the board.
    private final int level;
    private final int row;
    private final int col;
    // The chess piece that is on this square, if any.
    private Piece piece;
    // Whether this square is currently active (selected or possible move).
    public boolean isActive;
    // The original color of the square (light or dark).
    private Color originalColor;

    /**
     * Constructs a square with specified row and column.
     *
     * @param row The row of the square on the chessboard.
     * @param col The column of the square on the chessboard.
     */
    public Square(int level, int row, int col) {
        this.level = level;
        this.row = row;
        this.col = col;
        originalColor = (row + col) % 2 == 0 ? DARK_COLOR : LIGHT_COLOR;
        setBackground(originalColor);
        // Set fixed size in the constructor
        setPreferredSize(new Dimension(60, 60)); // Adjust the size as needed
    }

    /**
     * Sets the active state of the square and triggers a repaint.
     *
     * @param active Whether the square is active.
     */
    public void setActive(boolean active) {
        this.isActive = active;
        repaint(); // Trigger a repaint to update the visual state of the square
    }

    /**
     * Custom painting for the square.
     *
     * @param g The Graphics object to paint on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call super.paintComponent to handle default painting behavior
        Graphics2D g2d = (Graphics2D) g.create();

        // Set rendering hints for quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint the background of the square. Remove the level-based opacity to ensure consistency across all levels.
        g2d.setColor(originalColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the piece if it exists
        if (piece != null) {
            drawPiece(g2d);
        }

        // Highlight the square if it is active
        if (isActive) {
            g2d.setColor(HIGHLIGHT_COLOR);
            g2d.setStroke(new BasicStroke(4.0f));
            g2d.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        }

        g2d.dispose(); // Dispose of the graphics object to release system resources
    }

    private void drawPiece(Graphics2D g2d) {
        // Set the font for drawing the piece
        g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
        FontMetrics metrics = g2d.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(piece.getImage())) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        int padding = 5;
        int rectWidth = metrics.stringWidth(piece.getImage()) + padding * 2;
        int rectHeight = metrics.getHeight() + padding;

        // Draw the piece's background
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x - padding, y - metrics.getAscent() - padding / 2, rectWidth, rectHeight);

        // Draw the piece's text
        g2d.setColor(piece.getOwner().isWhite() ? Color.WHITE : Color.BLACK);
        g2d.drawString(piece.getImage(), x, y);
    }


    // Getters and setters for the square's properties.
    public int getLevel() {
        return level;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        repaint();
    }

    public Piece getPiece() {
        return piece;
    }

    /**
     * Toggles the highlight status of the square and updates its background color.
     */
    public void toggleHighlight() {
        isActive = !isActive;
        updateBackgroundColor();
        repaint();
    }

    /**
     * Updates the background color of the square based on its active status.
     */
    private void updateBackgroundColor() {
        if (isActive) {
            setBackground(HIGHLIGHT_COLOR);
        } else {
            setBackground(originalColor);
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
