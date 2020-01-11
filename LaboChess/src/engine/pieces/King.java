package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

public class King extends Piece {
    public King(PlayerColor playerColor) {
        super(playerColor);
    }

    public String textValue(){
        return toString();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public Move move(Square from, Square to) {
        int deltaX = Math.abs(to.getPosX() - from.getPosX());
        int deltaY = Math.abs(to.getPosY() - from.getPosY());

        if ((deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 1)) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(from, to, false, move);
        }

        if ((to.getPosX() - from.getPosX() == 3 && deltaY == 0)) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.SMALLCASTLING);
            return new Move(from, to, false, move);
        }

        if ((to.getPosX() - from.getPosX() == -4 && deltaY == 0)) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.BIGCASTLING);
            return new Move(from, to, false, move);
        }
        return null;
    }

    @Override
    public String toString() {
        return "King";
    }
}
