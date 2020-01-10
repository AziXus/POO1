package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;

/**
 * class Board that will control the view and verify the mouvement asked by the user
 */
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
     * @param fromX entier étant la position de départ de la pièce en abscisse
     * @param fromY entier étant la position de départ de la pièce en ordonnée
     * @param toX entier étant la postion d'arrivée de la pièce en abscisse
     * @param toY entier étant la postion d'arrivée de la pièce en ordonnée
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
        // Clean the board
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                board[x][y] = null;
            }
        }

        initBoard();

        //Initialse the different pieces on the view
        for (int x = 0; x < 8; ++x) {
            for (int y = 0; y < 8; ++y) {
                Piece p = board[x][y];
                if (p != null)
                    view.putPiece(p.getPieceType(), p.getPlayerColor(), x, y);
            }
        }
    }

    /**
     * Function that will initiate the different cases with pieces on the board
     */
    private void initBoard() {
        board[0][0] = new Rook(PlayerColor.WHITE);
        board[1][0] = new Knight(PlayerColor.WHITE);
        board[2][0] = new Bishop(PlayerColor.WHITE);
        board[3][0] = new Queen(PlayerColor.WHITE);
        //Keep the king of the white player for the check
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

    /**
     * Constructor by default of the classe Board will initiate the view and the variable board
     */
    public Board() {
        initBoard();
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

    /**
     * Function that can check if a king is a check position
     * @param color variable of type playercolor that will choose the color of the king that has to be verified
     * @return true if the king is in a check position, false otherwise
     */
    private boolean check(PlayerColor color) {
        //Variables that will have the postion of the king on the board
        int posXKing = 0;
        int posYKing = 0;
        //Loop allowing to find the king that we are searching for on the board
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
        //Loop that will make all the pieces of the opponent attack to see if it can get to the king
        //If one of the pieces can reach the king the king is on a check postion
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = board[x][y];
                if(p != null && p.getPlayerColor() != color) {
                    //Move of the piece to the position of the king
                    Move m = p.move(x, y, posXKing, posYKing);
                    //If the attack is not one of the moves return or if the move is impossible continuation of the loop
                    if (m == null || !m.getType().contains(MovementType.ATTACK))
                        continue;
                    //If the piece can jump(Knight) the function is true with a knight the path don't has to be checked
                    //If the piece cannot jump check if the path is clear that means the path don't have any pieces on it
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

    /**
     * Function that will move a piece on the board
     * @param oldX int containing the value of the old postion of the piece on the X axis
     * @param oldY int containing the value of the old postion of the piece on the Y axis
     * @param newX int containing the value of the new postion of the piece on the X axis
     * @param newY int containing the value of the new postion of the piece on the Y axis
     */
    private void movePiece2(int oldX, int oldY, int newX, int newY) {
        board[newX][newY] = board[oldX][oldY];
        board[oldX][oldY] = null;
    }

    /**
     * Function that will make a piece move
     * @param m object of type move that represents the move done by a piece
     * @return true of the move is valid, false otherwise
     */
    private boolean makeMove(Move m) {
        //If the path is not clear the move is invalid
        if (!m.isCanJump() && !isPathClear(m.getSrcX(), m.getSrcY(), m.getDestX(), m.getDestY()))
            return false;

        //Get the piece that has to be moved
        Piece p = board[m.getSrcX()][m.getSrcY()];
        //Move the piece on the board
        movePiece2(m.getSrcX(), m.getSrcY(), m.getDestX(), m.getDestY());
        //Update the view
        view.removePiece(m.getSrcX(), m.getSrcY());
        view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getDestX(), m.getDestY());

        //If with the new movement the king of the same color is in a check the move is revert
        if (check(p.getPlayerColor())) {
            revertMove(m);
            return false;
        }

        //Save the coordinates of the lastMove
        lastMove[0] = m.getDestX();
        lastMove[1] = m.getDestY();
        //The piece has moved so the boolean that indicates of a piece is on her first move has to be set at false
        p.hasMoved();
        //If in the list of moves that the piece has return the movementType PROMOTE is in ask for a promotion
        if (m.getType().contains(MovementType.PROMOTE)) {
            p = view.askUser("Promotion", "Which promotion would you like", new Rook(p.getPlayerColor()), new Bishop(p.getPlayerColor()), new Queen(p.getPlayerColor()), new Knight(p.getPlayerColor()));
            //The piece has changed the board has to be updated
            board[m.getDestX()][m.getDestY()] = p;
            view.removePiece(m.getSrcX(), m.getSrcY());
            view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getDestX(), m.getDestY());
        }

        return true;
    }

    /**
     * Function that will execute the enPassant move
     * @param m object of type move that represents the move done by a piece
     * @return true if the move is valid, false otherwise
     */
    private boolean makeEnPassant(Move m) {
        //By the color of the pawn we get the direction that has to be used on the Y axis
        int directionY = board[m.getSrcX()][m.getSrcY()].getPlayerColor() == PlayerColor.WHITE ? 1 : -1;
        //Check that the destination of the pawn is on the same x axis has the last move and on the Y axis + 1 has it is an enPassant
        //Check that the last move has been done by pawn beauces if not the move enPassant is invalid
        if(lastMove[0] == m.getDestX() && lastMove[1] + directionY == m.getDestY() && board[lastMove[0]][lastMove[1]].getPieceType() == PieceType.PAWN) {
            int[] lastMoveTemp = new int[2];
            //We have to create as temporary move because if the move of the pawn is valid(no check) the last move will be replaced
            lastMoveTemp[0] = lastMove[0];
            lastMoveTemp[1] = lastMove[1];
            if(makeMove(m)) {
                //Update the view by removing the piece that has been eaten
                board[lastMoveTemp[0]][lastMoveTemp[1]] = null;
                view.removePiece(lastMoveTemp[0] , lastMoveTemp[1]);
                return true;
            }
        }

        return false;
    }

    /**
     * Function that implements the smallCatsling move
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return true if the move is valid, false otherwise
     */
    private boolean makeSmallCastling(int oldX, int oldY, int newX, int newY) {
        Piece king = board[oldX][oldY];
        Piece rook = board[newX][newY];

        if (rook == null || rook.getPieceType() != PieceType.ROOK)
            return false;

        if (!king.isFirstMove() || !rook.isFirstMove())
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
                view.putPiece(PieceType.ROOK, king.getPlayerColor(), oldX + 1, oldY);
                return true;
            }
            revertMove(firstCase);
        }
        return false;
    }

    /**
     * Function that will revert the move passed by parmater
     * @param m object of type move that represents the move done by a piece
     */
    private void revertMove(Move m) {
        Piece p = board[m.getDestX()][m.getDestY()];
        //Move the piece to is old position again
        movePiece2(m.getDestX(), m.getDestY(), m.getSrcX(), m.getSrcY());
        view.removePiece(m.getDestX(), m.getDestY());
        view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getSrcX(), m.getSrcY());
    }

    /**
     * Function that will make the bigCastling move
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return true if the move is valid, false otherwise
     */
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
                    //The king is placed in the case oldX - 3 after a bigCastling so the old place has to be set to null as the king is no longer there
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

    /**
     * Function that will make the piece move from a square to another
     * @param oldX int representing initial position on the x axis
     * @param oldY int representing initial position on the Y axis
     * @param newX int representing the desired position on the x axis
     * @param newY int representing the desired position on the x axis
     * @return true if the move is valid, false otherwise
     */
    public boolean movePiece(int oldX, int oldY, int newX, int newY){
        if(board[oldX][oldY] == null)
            return false;

        Piece p = board[oldX][oldY];

        //Ask to a piece if the move is valid
        Move movePiece = p.move(oldX, oldY, newX, newY);
        //if the piece doesn't return a move that makes him invalid
        if(movePiece == null){
            return false;
        }

        boolean validMove = false;
        //movePiece returns a list of move available ba the piece
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
        if (check(p.getPlayerColor())) {
            view.displayMessage("Check");
        }
        return validMove;
    }

    /**
     *
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return
     */
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
