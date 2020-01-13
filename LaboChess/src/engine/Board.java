package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

/**
 * class Board that will control the view and verify the movement asked by the user
 */
public class Board implements ChessController {
    private final Piece[][] board = new Piece[8][8];
    private Move lastMove = new Move(new Square(-1,-1), new Square(-1,-1), false);
    private King white;
    private King black;
    private PlayerColor currentPlayer;
    private ChessView view;

    /**
     * Constructor by default of the class Board will initiate the view and the variable board
     */
    public Board() {
        currentPlayer = PlayerColor.WHITE;
        initBoard();
    }

    /**
     * Return the piece on the board
     * @param s position of the piece
     * @return Piece on the coordinate of the square.
     */
    private Piece getPiece(Square s) {
        return board[s.getPosX()][s.getPosY()];
    }

    /**
     * Set a piece on the board
     * @param s position of the piece
     * @param p piece to set on the board
     */
    private void setPiece(Square s, Piece p) {
        board[s.getPosX()][s.getPosY()] = p;
    }

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
     * Démarre une nouvelle partie. L'échiquier doit être remis dans sa position initiale.
     */
    public void newGame() {
        currentPlayer = PlayerColor.WHITE;
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
     * Appelé lorsque l'utilisateur a demandé un déplacement de la position X à la position Y.
     * La position 0, 0 est en bas à gauche de l'échiquier.
     * @param fromX entier étant la position de départ de la pièce en abscisse
     * @param fromY entier étant la position de départ de la pièce en ordonnée
     * @param toX entier étant la postion d'arrivée de la pièce en abscisse
     * @param toY entier étant la postion d'arrivée de la pièce en ordonnée
     * @return true si le mouvement a pu avoir lieu, false dans le cas contraire.
     */
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Square from = new Square(fromX, fromY);
        Square to = new Square(toX, toY);

        Piece p = getPiece(from);

        if(p == null)
            return false;

        if (p.getPlayerColor() != currentPlayer)
            return false;

        //Ask to a piece if the move is valid
        Move movePiece = p.move(from, to);
        //if the piece doesn't return a move that makes him invalid
        if(movePiece.getType().contains(MovementType.NONE)){
            return false;
        }

        boolean validMove = false;
        //movePiece returns a list of move available ba the piece
        for(MovementType t : movePiece.getType()){
            switch(t){
                case MOVE:
                    if (getPiece(movePiece.getDest()) == null)
                        validMove = makeMove(movePiece);
                    break;

                case ATTACK:
                    if (getPiece(movePiece.getDest()) != null &&
                            getPiece(movePiece.getDest()).getPlayerColor() != p.getPlayerColor())
                        validMove = makeMove(movePiece);
                    break;

                case ENPASSANT:
                    validMove = makeEnPassant(movePiece);
                    break;

                case SMALLCASTLING:
                    validMove = makeSmallCastling(from, to);
                    break;

                case BIGCASTLING:
                    validMove = makeBigCastling(from, to);
                    break;
            }

            if (validMove)
                break;
        }

        if (validMove)
            nextTurn();

        return validMove;
    }

    /**
    * Passe au tour suivant en changeant le joueur courant.
    */
    public void nextTurn() {
        if (currentPlayer == PlayerColor.WHITE)
            currentPlayer = PlayerColor.BLACK;
        else if (currentPlayer == PlayerColor.BLACK)
            currentPlayer = PlayerColor.WHITE;
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
                if (board[x][y] == white) {
                    posXKing = x;
                    posYKing = y;
                }
                if (board[x][y] == black) {
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
                    Move m = p.move(new Square(x, y), new Square(posXKing, posYKing));
                    //If the attack is not one of the moves return or if the move is impossible continuation of the loop
                    if (m.getType().contains(MovementType.NONE) || !m.getType().contains(MovementType.ATTACK))
                        continue;
                    //If the piece can jump(Knight) the function is true with a knight the path don't has to be checked
                    //If the piece cannot jump check if the path is clear that means the path don't have any pieces on it
                    if (m.isCanJump() || isPathClear(m.getSrc(), m.getDest()))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Function that will move a piece on the board
     * @param oldSquare position of the initial piece on the board
     * @param newSquare position of the destination to move the piece on the board
     */
    private void movePiece(Square oldSquare, Square newSquare) {
        setPiece(newSquare, getPiece(oldSquare));
        setPiece(oldSquare, null);
    }

    /**
     * Function that will make a piece move
     * @param m object of type move that represents the move done by a piece
     * @return true of the move is valid, false otherwise
     */
    private boolean makeMove(Move m) {
        //If the path is not clear the move is invalid
        if (!m.isCanJump() && !isPathClear(m.getSrc(), m.getDest()))
            return false;

        //Get the piece that has to be moved
        Piece p = getPiece(m.getSrc());
        //Save the destination Piece
        Piece pDest = getPiece(m.getDest());
        //Move the piece on the board
        movePiece(m.getSrc(), m.getDest());
        //Update the view
        view.removePiece(m.getSrcX(), m.getSrcY());
        view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getDestX(), m.getDestY());

        //If with the new movement the king of the same color is in a check the move is revert
        if (check(p.getPlayerColor())) {
            view.displayMessage("Check");
            revertMove(m, pDest);
            return false;
        }

        //Save the last move
        lastMove = m;
        //The piece has moved so the boolean that indicates of a piece is on her first move has to be set at false
        p.hasMoved();
        //If in the list of moves that the piece has return the movementType PROMOTE is in ask for a promotion
        if (m.getType() != null && m.getType().contains(MovementType.PROMOTE)) {
            p = view.askUser("Promotion", "Which promotion would you like", new Rook(p.getPlayerColor()), new Bishop(p.getPlayerColor()), new Queen(p.getPlayerColor()), new Knight(p.getPlayerColor()));
            //The piece has changed the board has to be updated
            setPiece(m.getDest(), p);
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
        if(lastMove.getDestX() == m.getDestX() && lastMove.getDestY() + directionY == m.getDestY() &&
                getPiece(lastMove.getDest()).getPieceType() == PieceType.PAWN) {
            //We have to create as temporary move because if the move of the pawn is valid(no check) the last move will be replaced
            Move lastMoveTemp = lastMove;
            if(makeMove(m)) {
                //Update the view by removing the piece that has been eaten
                setPiece(lastMoveTemp.getDest(), null);
                view.removePiece(lastMoveTemp.getDestX() , lastMoveTemp.getDestY());
                return true;
            }
        }

        return false;
    }

    /**
     * Function that implements the smallCastling move
     * @param from object of type square that represents the square where the piece start to move
     * @param to object of type square that reprents the square where the piece wants to go
     * @return true if the move is valid, false otherwise
     */
    private boolean makeSmallCastling(Square from, Square to) {
        Piece king = getPiece(from);
        Piece rook = getPiece(to);

        if (rook == null || rook.getPieceType() != PieceType.ROOK)
            return false;

        if (!king.isFirstMove() || !rook.isFirstMove())
            return false;

        if (!isPathClear(from, to))
            return false;

        //TODO Change the makeMove
//        ArrayList<MovementType> m = new ArrayList<>();
//        m.add(MovementType.MOVE);

        Move firstCase = new Move(from.getPosX(), from.getPosY(), from.getPosX() + 1, from.getPosY(), false);
        Move secondCase = new Move(from.getPosX() + 1, from.getPosY(), from.getPosX() + 2, from.getPosY(), false);
        //We move the king in the different cases when he does a smallCastling if the king is on check on one of the cases
        //the move will be invalid
        if(makeMove(firstCase)){
            if(makeMove(secondCase)) {
                //The king is placed in the oldX + 2 after a smallCastling so the old place has to be set to null as the king is no longer there
                setPiece(from, null);
                //Move the rook to the right place after a smallCastling
                board[from.getPosX() + 1][from.getPosY()] = getPiece(to);
                //The rook isn't in the last place anymore
                setPiece(to, null);
                //Remove the old place of the rook on the view
                view.removePiece(to.getPosX(), to.getPosY());
                //Add the new place of the rook on the view
                view.putPiece(PieceType.ROOK, king.getPlayerColor(), from.getPosX() + 1, from.getPosY());
                return true;
            }
            revertMove(firstCase);
        }
        return false;
    }

    /**
     * Function that will make the bigCastling move
     * @param from object of type square that represents the square where the piece start to move
     * @param to object of type square that reprents the square where the piece wants to go
     * @return true if the move is valid, false otherwise
     */
    private boolean makeBigCastling(Square from, Square to) {
        Piece p = getPiece(from);
        Piece rook = getPiece(to);

        if (rook == null || rook.getPieceType() != PieceType.ROOK)
            return false;

        if (!p.isFirstMove() || !rook.isFirstMove())
            return false;

        if (!isPathClear(from, to))
            return false;

        //TODO Change the oldY makeMove
//        ArrayList<MovementType> m = new ArrayList<>();
//        m.add(MovementType.MOVE);

        Move firstCase = new Move(from.getPosX(), from.getPosY(), from.getPosX() - 1, from.getPosY(), false);
        Move secondCase = new Move(from.getPosX() - 1, from.getPosY(), from.getPosX() - 2, from.getPosY(), false);

        if(makeMove(firstCase)){
            if(makeMove(secondCase)) {
                //The king is placed in the case oldX - 3 after a bigCastling so the old place has to be set to null as the king is no longer there
                setPiece(from, null);
                //Move the rook to the right place after a smallCastling
                board[from.getPosX() - 1][from.getPosY()] = getPiece(to);
                //The rook isn't in the last place anymore
                setPiece(to, null);
                //Remove the old place of the rook on the view
                view.removePiece(to.getPosX(), to.getPosY());
                //Add the new place of the rook on the view
                view.putPiece(PieceType.ROOK, p.getPlayerColor(), from.getPosX() - 1, from.getPosY());
                return true;
            }
            revertMove(firstCase);
        }

        return false;
    }

    /**
     * Function that will revert the move passed by parameter
     * @param m object of type move that represents the move done by a piece
     */
    private void revertMove(Move m) {
        Piece p = board[m.getDestX()][m.getDestY()];
        //Move the piece to is old position again
        movePiece(m.getDest(), m.getSrc());
        view.removePiece(m.getDestX(), m.getDestY());
        view.putPiece(p.getPieceType(), p.getPlayerColor(), m.getSrcX(), m.getSrcY());
    }

    /**
     * Function that will revert the move passed by parameter and replace the old Piece
     * @param m object of type move that represents the move done by a piece
     * @param dest Piece of destination to replace
     */
    private void revertMove(Move m, Piece dest) {
        revertMove(m);

        if (dest != null) {
            setPiece(m.getDest(), dest);
            view.putPiece(dest.getPieceType(), dest.getPlayerColor(), m.getDestX(), m.getDestY());
        }
    }

    /**
     * Determine if a piece is on the path of Square from and Square to
     * @param from source square
     * @param to destination square
     * @return true if no piece on the path, false otherwise
     */
    private boolean isPathClear(Square from, Square to) {
        int xInc = 0,
            yInc = 0;
        int fromX = from.getPosX(),
            fromY = from.getPosY(),
            toX   = to.getPosX(),
            toY   = to.getPosY();

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
