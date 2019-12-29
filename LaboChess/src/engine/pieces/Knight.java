package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Square;

public class Knight extends Piece {
    public Knight(PlayerColor playerColor) {
        super(playerColor);
    }

    public String textValue(){
        return toString();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2))
            return true;

        return false;
    }

    @Override
    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
        return move(board, fromX, fromY, toX, toY);
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
