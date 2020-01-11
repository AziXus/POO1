package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

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
    public Move move(Square from, Square to) {
        int deltaX = Math.abs(to.getPosX() - from.getPosX());
        int deltaY = Math.abs(to.getPosY() - from.getPosY());

        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(from, to, true, move);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
