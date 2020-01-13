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
public class Bishop extends Piece {

    /**
     * Constructor of the Bishop
     * @param playerColor color of the player that owns the piece
     */
    public Bishop(PlayerColor playerColor) {
        super(playerColor);
    }

    /**
     * Override that returns a BISHOP;
     * @return type of the piece
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    /**
     * Generate a valid Move for a Bishop
     * @param from source Square
     * @param to destination Square
     * @return a Move if possible, false otherwise.
     */
    @Override
    public Move move(Square from, Square to) {
        if (from == to)
            return null;

        int moveX = from.getPosX() - to.getPosX();
        int moveY = from.getPosY() - to.getPosY();
        ArrayList<MovementType> move = new ArrayList<>();
        //To move in diagonal the difference between fromY and toY as to be the same as the difference between fromX and toX
        if(Math.abs(moveX) == Math.abs(moveY)) {
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
        return "Bishop";
    }
}
