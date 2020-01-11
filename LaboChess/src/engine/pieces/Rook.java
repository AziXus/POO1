package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(PlayerColor playerColor) {
        super(playerColor);
    }

    public String textValue(){
        return toString();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public Move move(Square from, Square to) {
        if (from == to)
            return null;

        //Is move on the right or left same Y
        if(from.getPosY() == to.getPosY()) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(from, to, false, move);
        }
        //Is move up down X is still the same
        if(from.getPosX() == to.getPosX()) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(from, to, false, move);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Rook";
    }
}
