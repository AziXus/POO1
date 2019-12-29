package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import chess.views.BaseView;
import engine.pieces.Pawn;
import engine.pieces.Piece;

import java.util.LinkedList;

public class ChessGame implements ChessController {
    private Board board;
    private ChessView view;

    /**
     * Démarre la logique (contrôleur) du programme.
     * Appelé une fois (voir Chess.main())
     * @param view la vue à utiliser
     */
    public void start(ChessView view) {
        this.view = view;

        newGame();
        view.startView();
    }

    /**
     * Appelé lorsque l'utilisateur a demandé un déplacement de la position X à la position Y.
     * La position 0, 0 est en bas à gauche de l'échiquier.
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return true si le mouvement a pu avoir lieu, false dans le cas contraire.
     */
    public boolean move(int fromX, int fromY, int toX, int toY) {
        //Check coordinate

                if(board.movePiece(fromX, fromY, toX, toY)) {
                    view.removePiece(fromX, fromY);
                    view.putPiece(PieceType.PAWN, board.getPiece(toX, toY).getPlayerColor(), toX, toY);
                    return true;
                }

        return false;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    public void newGame() {
        board = new Board();

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                Piece p = board.getPiece(x, y);
                if (p != null) {
                    view.putPiece(p.getPieceType(), p.getPlayerColor(), x, y);
                }
            }
        }
    }
}
