package engine.pieces;


import chess.PlayerColor;
import engine.Board;
import engine.Square;
import java.lang.Math.*;

public class Bishop extends Piece {
    public Bishop(PlayerColor playerColor) {
        super(playerColor);
    }

    @Override
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        int moveX = fromX - toX;
        int moveY = fromY - toY;
        if(Math.abs(moveX) == Math.abs(moveY))
            return true;
        return false;
    }

    @Override
    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }
}
