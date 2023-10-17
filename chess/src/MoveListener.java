import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveListener extends MouseAdapter {
    private Chess game;
    private Square startSquare;

    @Override
    public void mouseClicked(MouseEvent e) {
        Square square = (Square) e.getSource();
        square.toggleHighlight();
    }
}
