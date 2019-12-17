package engine;

import chess.PlayerColor;
import engine.pieces.*;

public class Board {
    private final Piece[][] board = new Piece[8][8];

    public Board(){
        board[0][0] = new Rook(PlayerColor.WHITE, 0, 0);
        board[1][0] = new Knight(PlayerColor.WHITE, 1, 0);
        board[2][0] = new Bishop(PlayerColor.WHITE, 2, 0);
        board[3][0] = new Queen(PlayerColor.WHITE, 3, 0);
        board[4][0] = new King(PlayerColor.WHITE, 4, 0);
        board[5][0] = new Bishop(PlayerColor.WHITE, 5, 0);
        board[6][0] = new Knight(PlayerColor.WHITE, 6, 0);
        board[7][0] = new Rook(PlayerColor.WHITE, 7, 0);

        for(int i = 0; i < 8; i++){
            board[i][1] = new Rook(PlayerColor.WHITE, i, 1);
        }


        board[0][7] = new Rook(PlayerColor.BLACK, 0, 7);
        board[1][7] = new Knight(PlayerColor.BLACK, 1, 7);
        board[2][7] = new Bishop(PlayerColor.BLACK, 2, 7);
        board[3][7] = new Queen(PlayerColor.BLACK, 3, 7);
        board[4][7] = new King(PlayerColor.BLACK, 4, 7);
        board[5][7] = new Bishop(PlayerColor.BLACK, 5, 7);
        board[6][7] = new Knight(PlayerColor.BLACK, 6, 7);
        board[7][7] = new Rook(PlayerColor.BLACK, 7, 7);

        for(int i = 0; i < 8; i++){
            board[i][6] = new Rook(PlayerColor.BLACK, i, 6);
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
        board[newX][newY] = board[oldX][oldY];
        board[oldX][oldY] = null;
        return true;
    }
}
