package engine.pieces;


import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(PlayerColor playerColor) {
        super(playerColor);
    }

    public String textValue(){
        return toString();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public Move move(Square from, Square to) {
        if (from == to)
            return null;

        int moveX = from.getPosX() - to.getPosX();
        int moveY = from.getPosY() - to.getPosY();
        //To move in diagonal the difference between fromY and toY as to be the same as the difference between fromX and toX
        if(Math.abs(moveX) == Math.abs(moveY)) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(from, to, false, move);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
