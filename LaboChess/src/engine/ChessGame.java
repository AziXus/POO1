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

    private void initBoardView(ChessView view, PlayerColor playerColor) {
        int firstLine;
        int secondLine;
        if (playerColor == PlayerColor.WHITE) {
            firstLine = 0;
            secondLine = firstLine + 1;
        } else {
            firstLine = 7;
            secondLine = firstLine - 1;
        }

        view.putPiece(PieceType.ROOK,   playerColor, 0, firstLine);
        view.putPiece(PieceType.KNIGHT, playerColor, 1, firstLine);
        view.putPiece(PieceType.BISHOP, playerColor, 2, firstLine);
        view.putPiece(PieceType.QUEEN,  playerColor, 3, firstLine);
        view.putPiece(PieceType.KING,   playerColor, 4, firstLine);
        view.putPiece(PieceType.BISHOP, playerColor, 5, firstLine);
        view.putPiece(PieceType.KNIGHT, playerColor, 6, firstLine);
        view.putPiece(PieceType.ROOK,   playerColor, 7, firstLine);

        for (int i = 0; i < 8; ++i) {
            view.putPiece(PieceType.PAWN, playerColor, i, secondLine);
        }
    }

    /**
     * Démarre la logique (contrôleur) du programme.
     * Appelé une fois (voir Chess.main())
     * @param view la vue à utiliser
     */
    public void start(ChessView view) {
        this.view = view;

        // Créer toutes les pièces, puis les placer dans la view
        initBoardView(view, PlayerColor.WHITE);
        initBoardView(view, PlayerColor.BLACK);

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

    }
}
