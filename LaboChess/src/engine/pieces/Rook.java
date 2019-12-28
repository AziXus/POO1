package engine.pieces;

import chess.PlayerColor;
import engine.Board;
import engine.Square;

public class Rook extends Piece {

    public Rook(PlayerColor playerColor) {
        super(playerColor);
    }

    @Override
    public boolean hasPieceOnMouvement(Board board, int toX, int toY) {
        return false;
    }

    @Override
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        //Is move on the right or left same Y
        if(fromY == toY)
            return true;
        //Is move up down X is still the same
        if(fromX == toX)
            return true;
        return false;
    }

    @Override
    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
