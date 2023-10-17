import javax.swing.JPanel;
import java.awt.Color;

public class Square extends JPanel {
    private int row;
    private int col;
    private Piece piece;
    private boolean isActive;
    private Color originalColor;

    public Square(int var1, int var2) {
        this.row = var1;
        this.col = var2;

        // Set the colour
        if ((row + col) % 2 == 0) {
            originalColor = Color.WHITE;
        } else {
            originalColor = Color.BLACK;
        }
        setBackground(originalColor);
    }

    public void setActive(boolean var1) {
        this.isActive = var1;
    }

    public void paintComponent(java.awt.Graphics var1) {
        super.paintComponent(var1);
        // Paint logic here
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPiece(Piece var1) {
        this.piece = var1;
    }

    public Piece getPiece() {
        return piece;
    }

    public void toggleHighlight() {
        if (getBackground().equals(originalColor)) {
            setBackground(Color.YELLOW); // Highlight with yellow when clicked
        } else {
            setBackground(originalColor); // Reset to original color
        }
    }
}
