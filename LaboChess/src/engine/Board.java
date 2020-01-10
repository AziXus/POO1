package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;

public class Board implements ChessController {
    private final Piece[][] board = new Piece[8][8];
    private int[] lastMove = new int[2];
    private King white;
    private King black;
    //private Move move;

    private ChessView view;

    /**
     * Démarre la logique (contrôleur) du programme.
     * Appelé une fois (voir Chess.main())
     * @param view la vue à utiliser
     */
    public void start(ChessView view) {
        this.view = view;
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
        if(movePiece(fromX, fromY, toX, toY)) {
            Piece p = board[toX][toY];


            return true;
        }

        return false;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    public void newGame() {
//        currentPlayer = PlayerColor.WHITE;

        // Clean the board
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                board[x][y] = null;
            }
        }

        initBoard();

        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                Piece p = board[x][y];
                if (p != null)
                    view.putPiece(p.getPieceType(), p.getPlayerColor(), x, y);
            }
        }
    }

    private void initBoard() {
        board[0][0] = new Rook(PlayerColor.WHITE);
        board[1][0] = new Knight(PlayerColor.WHITE);
        board[2][0] = new Bishop(PlayerColor.WHITE);
        board[3][0] = new Queen(PlayerColor.WHITE);
        white = new King(PlayerColor.WHITE);
        board[4][0] = white;
        board[5][0] = new Bishop(PlayerColor.WHITE);
        board[6][0] = new Knight(PlayerColor.WHITE);
        board[7][0] = new Rook(PlayerColor.WHITE);

        for(int i = 0; i < 8; i++){
            board[i][1] = new Pawn(PlayerColor.WHITE);
        }

        board[0][7] = new Rook(PlayerColor.BLACK);
        board[1][7] = new Knight(PlayerColor.BLACK);
        board[2][7] = new Bishop(PlayerColor.BLACK);
        board[3][7] = new Queen(PlayerColor.BLACK);
        black = new King(PlayerColor.BLACK);
        board[4][7] = black;
        board[5][7] = new Bishop(PlayerColor.BLACK);
        board[6][7] = new Knight(PlayerColor.BLACK);
        board[7][7] = new Rook(PlayerColor.BLACK);

        for(int i = 0; i < 8; i++){
            board[i][6] = new Pawn(PlayerColor.BLACK);
        }
    }

    public Board() {
        initBoard();
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    public void setPiece(Piece p, int x, int y) {
        board[x][y] = p;
    }

//    private boolean prisePassant(int oldX, int oldY, int newX, int newY){
//            if(board[lastMove[0]][lastMove[1]].getPieceType() != PieceType.PAWN){
//                return false;
//            }
//            if(board[oldX][oldY].attack(this, oldX, oldY, newX, newY)) {
//                board[newX][newY] = board[oldX][oldY];
//                board[oldX][oldY] = null;
//                board[lastMove[0]][lastMove[1]] = null;
//                view.removePiece(lastMove[0], lastMove[1]);
//            }
//            return true;
//    }
    private boolean check(PlayerColor color) {
        int posXKing = 0;
        int posYKing = 0;
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (color == PlayerColor.WHITE && board[x][y] == white) {
                        posXKing = x;
                        posYKing = y;
                    }
                    if (color == PlayerColor.BLACK && board[x][y] == black) {
                        posXKing = x;
                        posYKing = y;
                    }
                }
            }

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    Piece p = board[x][y];
                    if(p != null && p.getPlayerColor() != color) {
                        Move m = p.move(x, y, posXKing, posYKing);

                        //Si l'attaque n'est pas possible ou le move est invalide
                        if (m == null || !m.getType().contains(MovementType.ATTACK))
                            continue;

                        // Si la piece peut sauter ou que le chemin est libre
                        if (m.isCanJump() || isPathClear(m.getSrcX(), m.getSrcY(), m.getDestX(), m.getDestY()))
                            return true;
                    }
                }
            }
            return false;
    }

    private boolean prisePassant(){ return false;}

//    private boolean simuleMovement(int oldX, int oldY, int newX, int newY, Piece p) {
//        Move movePiece = p.move(this, oldX, oldY, newX, newY);
//        if (movePiece == null) {
//            return false;
//        }
//        for (MovementType t : movePiece.getType()) {
//            switch (t) {
//                case MOVE:
//                    if (board[newX][newY] == null) {
////                        if(Check(p.getPlayerColor()) && p.getPieceType() != PieceType.KING){
////                            return false;
////                        }
//                        //Save the lastMove
//                        board[newX][newY] = board[oldX][oldY];
//                        board[oldX][oldY] = null;
//                        return true;
//                    }
//                    break;
//                case ATTACK:
//                    if (oldX != newX && oldY != newY && board[newX][newY] != null) {
////                        if(Check(p.getPlayerColor())){
////                            return false;
////                        }
//                        board[newX][newY] = board[oldX][oldY];
//                        board[oldX][oldY] = null;
//                        return true;
//                    }
//                    break;
//                case SMALLCASTLING:
//
//            }
//        }
//        return false;
//    }

    private void revertMovement(int oldX, int oldY, int newX, int newY, Piece p){
        board[oldX][oldY] = board[newX][newY];
        board[newX][newY] = null;
    }

    private void movePiece2(int oldX, int oldY, int newX, int newY) {
        board[newX][newY] = board[oldX][oldY];
        board[oldX][oldY] = null;
    }

    private boolean makeMove(Move m) {
        if (!m.isCanJump() && !isPathClear(m.getSrcX(), m.getSrcY(), m.getDestX(), m.getDestY()))
            return false;

        Piece p = board[m.getSrcX()][m.getSrcY()];

        // Move the piece
        movePiece2(m.getSrcX(), m.getSrcY(), m.getDestX(), m.getDestY());
        view.removePiece(m.getSrcX(), m.getSrcY());
        view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getDestX(), m.getDestY());

        if (check(p.getPlayerColor())) {
            revertMove(m);
            return false;
        }

        //Save the lastMove
        lastMove[0] = m.getDestX();
        lastMove[1] = m.getDestY();

        p.hasMoved();

        if (m.getType().contains(MovementType.PROMOTE)) {
            p = view.<Piece>askUser("Promotion", "Which promotion would you like", new Rook(p.getPlayerColor()), new Bishop(p.getPlayerColor()), new Queen(p.getPlayerColor()), new Knight(p.getPlayerColor()));
            //The piece has changed the board has to be updated
            board[m.getDestX()][m.getDestY()] = p;
            view.removePiece(m.getSrcX(), m.getSrcY());
            view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getDestX(), m.getDestY());
        }

        return true;
    }

    private boolean makeEnPassant(Move m) {
        if(lastMove[0] == m.getDestX() && lastMove[1] - 1 == m.getDestY() && board[lastMove[0]][lastMove[1]].getPieceType() == PieceType.PAWN) {
            int[] lastMoveTemp = new int[2];
            //We have to create as temporary move because if the move of the pawn is valid(no check) the last move will be replaced
            lastMoveTemp[0] = lastMove[0];
            lastMoveTemp[1] = lastMove[1];
            if(makeMove(m)) {
                board[lastMoveTemp[0]][lastMoveTemp[1]] = null;
                view.removePiece(lastMoveTemp[0] , lastMoveTemp[1]);
                return true;
            }
        }

        return false;
    }

    private boolean makeSmallCastling(int oldX, int oldY, int newX, int newY) {
        Piece p = board[oldX][oldY];
        Piece rook = board[newX][newY];

        if (rook == null || rook.getPieceType() != PieceType.ROOK)
            return false;

        if (!p.isFirstMove() || !rook.isFirstMove())
            return false;

        if (!isPathClear(oldX, oldY, newX, newY))
            return false;

        //TODO Change the makeMove
        ArrayList<MovementType> m = new ArrayList<>();
        m.add(MovementType.MOVE);

        Move firstCase = new Move(oldX, oldY, oldX + 1, oldY, false, m);
        Move secondCase = new Move(oldX + 1, oldY, oldX + 2, newY, false, m);
        //We move the king in the different cases when he does a smallCastling if the king is on check on one of the cases
        //the move will be invalid
        if(makeMove(firstCase)){
            if(makeMove(secondCase)) {
                //The king is placed in the oldX + 2 after a smallCastling so the old place has to be set to null as the king is no longer there
                board[oldX][oldY] = null;
                //Move the rook to the right place after a smallCastling
                board[oldX + 1][oldY] = board[newX][newY];
                //The rook isn't in the last place anymore
                board[newX][newY] = null;
                //Remove the old place of the rook on the view
                view.removePiece(newX, newY);
                //Add the new place of the rook on the view
                view.putPiece(PieceType.ROOK, p.getPlayerColor(), oldX + 1, oldY);
                return true;
            }
            revertMove(firstCase);
        }
        return false;
    }

    private void revertMove(Move m) {
        Piece p = board[m.getDestX()][m.getDestY()];
        //If a move is invalid the move has to be reverted
        //If a check append with the move of the piece the move is reverted
        movePiece2(m.getDestX(), m.getDestY(), m.getSrcX(), m.getSrcY());

        view.removePiece(m.getDestX(), m.getDestY());
        view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getSrcX(), m.getSrcY());
    }

    private boolean makeBigCastling(int oldX, int oldY, int newX, int newY) {
        Piece p = board[oldX][oldY];
        Piece rook = board[newX][newY];

        if (rook == null || rook.getPieceType() != PieceType.ROOK)
            return false;

        if (!p.isFirstMove() || !rook.isFirstMove())
            return false;

        if (!isPathClear(oldX, oldY, newX, newY))
            return false;

        //TODO Change the makeMove
        ArrayList<MovementType> m = new ArrayList<>();
        m.add(MovementType.MOVE);

        Move firstCase = new Move(oldX, oldY, oldX - 1, oldY, false, m);
        Move secondCase = new Move(oldX - 1, oldY, oldX - 2, newY, false, m);
        Move thirdCase = new Move(oldX - 2, oldY, oldX - 3, newY, false, m);

        if(makeMove(firstCase)){
            if(makeMove(secondCase)) {
                if(makeMove(thirdCase)) {
                    //The king is placed in the oldX + 2 after a smallCastling so the old place has to be set to null as the king is no longer there
                    board[oldX][oldY] = null;
                    //Move the rook to the right place after a smallCastling
                    board[oldX + 1][oldY] = board[newX][newY];
                    //The rook isn't in the last place anymore
                    board[newX][newY] = null;
                    //Remove the old place of the rook on the view
                    view.removePiece(newX, newY);
                    //Add the new place of the rook on the view
                    view.putPiece(PieceType.ROOK, p.getPlayerColor(), oldX - 2, oldY);
                    return true;
                }
                revertMove(secondCase);
            }
            revertMove(firstCase);
        }
        return false;
    }

    public boolean movePiece(int oldX, int oldY, int newX, int newY){
        if(board[oldX][oldY] == null)
            return false;

        Piece p = board[oldX][oldY];

        Move movePiece = p.move(oldX, oldY, newX, newY);
        if(movePiece == null){
            return false;
        }

        boolean validMove = false;
        for(MovementType t : movePiece.getType()){
            switch(t){
                case MOVE:
                    if (board[movePiece.getDestX()][movePiece.getDestY()] == null)
                        validMove = makeMove(movePiece);
                    break;

                case ATTACK:
                    if (board[movePiece.getDestX()][movePiece.getDestY()] != null &&
                        board[movePiece.getDestX()][movePiece.getDestY()].getPlayerColor() != p.getPlayerColor())
                        validMove = makeMove(movePiece);
                    break;

                case ENPASSANT:
                    validMove = makeEnPassant(movePiece);
                    break;

                case SMALLCASTLING:
                    validMove = makeSmallCastling(oldX, oldY, newX, newY);
                    break;

                case BIGCASTLING:
                    validMove = makeBigCastling(oldX, oldY, newX, newY);
                    break;
            }

            if (validMove)
                break;
        }

        if (validMove) {
//            view.removePiece(oldX, oldY);
//            view.putPiece(p.getPieceType(), p.getPlayerColor(), newX, newY);
        }

        return validMove;
    }

    private boolean isPathClear(int fromX, int fromY, int toX, int toY) {
        int xInc = 0,
            yInc = 0;

        if (fromX > toX) {
            xInc = -1;
        } else if (fromX < toX) {
            xInc = 1;
        }
        if (fromY > toY) {
            yInc = -1;
        } else if (fromY < toY) {
            yInc = 1;
        }

        // Ignore first and last case
        fromX += xInc;
        fromY += yInc;
        while(fromX != toX || fromY != toY) {
            if (board[fromX][fromY] != null)
                return false;

            fromX += xInc;
            fromY += yInc;
        }

        return true;
    }
}
