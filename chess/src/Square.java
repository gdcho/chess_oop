import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        if (piece != null) {
            FontMetrics metrics = g.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(piece.getPiece())) / 2;
            int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

            g.setColor(Color.RED);
            g.drawString(piece.getPiece(), x, y);
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPiece(Piece var1) {
        this.piece = var1;
        repaint();
    }

    public Piece getPiece() {
        return piece;
    }

    public void toggleHighlight() {
        if (getBackground().equals(originalColor)) {
            setBackground(Color.YELLOW);
        } else {
            setBackground(originalColor);
        }
    }
}
