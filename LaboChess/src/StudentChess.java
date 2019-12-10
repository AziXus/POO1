import chess.*;
import chess.assets.*;
import chess.views.gui.*;
import engine.ChessGame;

public class StudentChess {
    public static void main(String[] args) {
        ChessController controller = new ChessGame();
        ChessView view = new GUIView(controller);

        controller.start(view);
    }
}
