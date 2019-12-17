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
    private final Piece[][] board = new Piece[8][8];
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
        //Check coordinates


        //Trouver piece a la position fromX et fromY
        Piece srcPiece = board[fromX][fromY];

        if (srcPiece == null)
            return false;

        Square targetSquare = new Square(toX, toY);
        LinkedList<Square> possibleMoves = srcPiece.validMoves(fromX, fromY);

        for (Square s : possibleMoves) {
            if (s.equals(targetSquare)) {
                //Make move
                view.removePiece(fromX, fromY);
                view.putPiece(PieceType.PAWN, PlayerColor.WHITE, toX, toY);
                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;
                srcPiece.setFirstMove(false);
            return true;
            }
        }

        return false;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    public void newGame() {
        board[0][1] = new Pawn(PlayerColor.WHITE);
        board[0][6] = new Pawn(PlayerColor.BLACK);

    }
}
