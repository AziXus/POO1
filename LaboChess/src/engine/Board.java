package engine;

import chess.PlayerColor;
import engine.pieces.*;

public class Board {
    private final Piece[][] board = new Piece[8][8];

    public Board(){
        board[0][0] = new Rook(PlayerColor.WHITE);
        board[1][0] = new Knight(PlayerColor.WHITE);
        board[2][0] = new Bishop(PlayerColor.WHITE);
        board[3][0] = new Queen(PlayerColor.WHITE);
        board[4][0] = new King(PlayerColor.WHITE);
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
        board[4][7] = new King(PlayerColor.BLACK);
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

    public boolean movePiece(int oldX, int oldY, int newX, int newY){
        if(board[oldX][oldY] == null)
            return false;

        if(board[newX][newY] != null){
            //If the piece in the place desired is the same color as the one playing move invalid
            if(board[newX][newY].getPlayerColor() == board[oldX][oldY].getPlayerColor())
                return false;
            if(board[oldX][oldY].attack(this, oldX, oldY, newX, newY)){
                board[newX][newY] = board[oldX][oldY];
                board[oldX][oldY] = null;
                return true;
            }
            return false;
        }

        if (board[oldX][oldY].move(this, oldX, oldY, newX, newY)) {
            board[newX][newY] = board[oldX][oldY];
            board[oldX][oldY] = null;
            return true;
        }
        return false;
    }
}
