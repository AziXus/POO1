package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

public class Knight extends Piece {

    /**
     * Constructor of the Knight
     * @param playerColor color of the player that owns the piece
     */
    public Knight(PlayerColor playerColor) {
        super(playerColor);
    }

    /**
     * Override that returns a KNIGHT;
     * @return type of the piece
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    /**
     * Generate a valid Move for a Knight
     * @param from source Square
     * @param to destination Square
     * @return a Move if possible, false otherwise.
     */
    @Override
    public Move move(Square from, Square to) {
        int deltaX = Math.abs(to.getPosX() - from.getPosX());
        int deltaY = Math.abs(to.getPosY() - from.getPosY());

        ArrayList<MovementType> move = new ArrayList<>();

        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
        } else {
            move.add(MovementType.NONE);
        }
        return new Move(from, to, true, move);
    }

    /**
     * Return the name of the piece
     * @return String containing the name
     */
    @Override
    public String toString() {
        return "Knight";
    }
}
