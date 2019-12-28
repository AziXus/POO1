package engine.pieces;

import chess.PlayerColor;
import engine.Board;
import engine.Square;
import java.lang.Math.*;

public class Queen extends Piece {

    public Queen(PlayerColor playerColor) {
        super(playerColor);
    }

    @Override
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        //Piece rookMove = new Rook(this.playerColor);
        //Piece BishopMove = new Bishop(this.playerColor);
        //Queen has the same move as the Bishop and the Rook
        int moveX = fromX - toX;
        int moveY = fromY - toY;
        if(Math.abs(moveX) == Math.abs(moveY))
            return true;
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
