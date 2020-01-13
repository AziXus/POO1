package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.Move;
import engine.move.MovementType;
import engine.move.Square;

import java.util.ArrayList;

/**
 *
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Rook extends Piece {

    /**
     * Constructor of the Rook
     * @param playerColor color of the player that owns the piece
     */
    public Rook(PlayerColor playerColor) {
        super(playerColor);
    }

    /**
     * Override that returns a ROOK;
     * @return type of the piece
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    /**
     * Generate a valid Move for a Rook
     * @param from source Square
     * @param to destination Square
     * @return a Move if possible, false otherwise.
     */
    @Override
    public Move move(Square from, Square to) {
        if (from == to)
            return null;
        ArrayList<MovementType> move = new ArrayList<>();
        //Is move on the right or left same Y
        if(from.getPosY() == to.getPosY()) {
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
        }
        //Is move up down X is still the same
        else if(from.getPosX() == to.getPosX()) {
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);

        } else {
            move.add(MovementType.NONE);
        }
        return new Move(from, to, false, move);
    }

    /**
     * Return the name of the piece
     * @return String containing the name
     */
    @Override
    public String toString() {
        return "Rook";
    }
}
