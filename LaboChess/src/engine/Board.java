package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.EnumMap;

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
            if(p.getPieceType() == PieceType.PAWN) {
                if(((Pawn)p).promotionAvailable(toX, toY)) {
                    p = view.<Piece>askUser("Promotion", "Which promotion would you like", new Rook(p.getPlayerColor()), new Bishop(p.getPlayerColor()), new Queen(p.getPlayerColor()), new Knight(p.getPlayerColor()));
                    //The piece has change the board has to be updated
                    board[toX][toY] = p;
                }
            }
            view.removePiece(fromX, fromY);
            view.putPiece(p.getPieceType(), p.getPlayerColor(), toX, toY);
            return true;
        }

        return false;
    }

    /**
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    public void newGame() {
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                Piece p = board[x][y];
                if (p != null)
                    view.putPiece(p.getPieceType(), p.getPlayerColor(), x, y);
            }
        }
    }
    public Board(){
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
    private boolean Check(PlayerColor color) {
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
                    if(p != null) {
                        //TODO the flag of the différent pieces is update so after a king move impossible to move the pawn by 2 square
                        Move movePiece = p.move(this, x, y, posXKing, posYKing);
                        if (movePiece != null && movePiece.getType().contains(MovementType.ATTACK) && isPathClear(x, y, posXKing, posYKing) && p.getPlayerColor() != color)
                            return true;
                    }
                }
            }
            return false;
    }

    private boolean prisePassant(){ return false;}

    private boolean simuleMovement(int oldX, int oldY, int newX, int newY, Piece p) {
        Move movePiece = p.move(this, oldX, oldY, newX, newY);
        if (movePiece == null) {
            return false;
        }
        for (MovementType t : movePiece.getType()) {
            switch (t) {
                case MOVE:
                    if (board[newX][newY] == null) {
//                        if(Check(p.getPlayerColor()) && p.getPieceType() != PieceType.KING){
//                            return false;
//                        }
                        board[newX][newY] = board[oldX][oldY];
                        board[oldX][oldY] = null;
                        //Save the lastMove

                        return true;
                    }
                    break;
                case ATTACK:
                    if (oldX != newX && oldY != newY && board[newX][newY] != null) {
//                        if(Check(p.getPlayerColor())){
//                            return false;
//                        }
                        board[newX][newY] = board[oldX][oldY];
                        board[oldX][oldY] = null;
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private void revertMovement(int oldX, int oldY, int newX, int newY, Piece p){
        board[oldX][oldY] = board[newX][newY];
        board[newX][newY] = null;
    }

    public boolean movePiece(int oldX, int oldY, int newX, int newY){
        if(board[oldX][oldY] == null)
            return false;

//        if(lastMove[0] == newX && lastMove[1] - 1 == newY && board[oldX][oldY].getPieceType() == PieceType.PAWN){
//            return prisePassant(oldX, oldY, newX, newY);
//        }
        Piece p = board[oldX][oldY];

        if(p.getPieceType() == PieceType.KING){
            if(simuleMovement(oldX, oldY, newX, newY, p)){
                if(Check(p.getPlayerColor())){
                    revertMovement(oldX, oldY, newX, newY, p);
                    return false;
                }
                lastMove[0] = newX;
                lastMove[1] = newY;
                p.hasMoved();
                return true;
            }
            return false;
        }

        Move movePiece = p.move(this, oldX, oldY, newX, newY);
        if(movePiece == null){
            return false;
        }

        for(MovementType t : movePiece.getType()){
            switch(t){
                case MOVE: if(board[newX][newY] == null){
                    if(isPathClear(oldX, oldY, newX, newY) || movePiece.isCanJump()) {
                        if(Check(p.getPlayerColor()) && p.getPieceType() != PieceType.KING){
                            return false;
                        }
                        board[newX][newY] = board[oldX][oldY];
                        board[oldX][oldY] = null;
                        //Save the lastMove
                        lastMove[0] = newX;
                        lastMove[1] = newY;
                        p.hasMoved();
                        return true;
                    }
                    return false;
                }
                break;
                case ATTACK: if(oldX != newX && oldY != newY && board[newX][newY] != null){
                    if(isPathClear(oldX, oldY, newX, newY) || movePiece.isCanJump()) {
                        if(Check(p.getPlayerColor())){
                            return false;
                        }
                        board[newX][newY] = board[oldX][oldY];
                        board[oldX][oldY] = null;
                        p.hasMoved();
                        return true;
                    }
                    return false;
                }
                break;
                case PRISEPASSANT: if(lastMove[0] == newX && lastMove[1] - 1 == newY && board[lastMove[0]][lastMove[1]].getPieceType() == PieceType.PAWN){
                    if(Check(p.getPlayerColor())){
                        return false;
                    }
                    board[newX][newY] = board[oldX][oldY];
                    board[oldX][oldY] = null;
                    board[lastMove[0]][lastMove[1]] = null;
                    view.removePiece(lastMove[0], lastMove[1]);
                    p.hasMoved();
                    return true;
                }
                break;
            }
        }
        return false;
//        if(board[newX][newY] != null){
//            //If the piece in the place desired is the same color as the one playing move invalid
//            if(board[newX][newY].getPlayerColor() == board[oldX][oldY].getPlayerColor())
//                return false;
//            if(board[oldX][oldY].attack(this, oldX, oldY, newX, newY)){
//                board[newX][newY] = board[oldX][oldY];
//                board[oldX][oldY] = null;
//                return true;
//            }
//            return false;
//        }
//
//        if(board[oldX][oldY].move(this, oldX, oldY, newX, newY)) {
//
//            return true;
//        }
//        return false;
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
