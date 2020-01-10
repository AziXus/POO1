package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Move;

public abstract class Piece implements ChessView.UserChoice {
    final PlayerColor playerColor;
    boolean firstMove = true;

    public Piece(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public abstract String textValue();

    public abstract PieceType getPieceType();

    public abstract boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY);

    public abstract Move move(int fromX, int fromY, int toX, int toY);

//    public abstract boolean attack(Board board, int fromX, int fromY, int toX, int toY);


    public void hasMoved() {
        if(firstMove) {
            this.firstMove = false;
        }
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public boolean isFirstMove(){
        return firstMove;
    }
}
