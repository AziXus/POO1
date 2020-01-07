import chess.*;
import chess.assets.*;
import chess.views.console.ConsoleView;
import chess.views.gui.*;
import engine.Board;
import engine.ChessGame;

public class StudentChess {
    public static void main(String[] args) {
        ChessController controller = new Board();
        ChessView view = new GUIView(controller);

        controller.start(view);
    }
}
