package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

public class Pawn extends Piece {

    /**
     * Constructor of the Pawn
     * @param playerColor color of the player that owns the piece
     */
    public Pawn(PlayerColor playerColor) {
        super(playerColor);
    }

    /**
     * Override that returns a PAWN;
     * @return type of the piece
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    /**
     * Determine whether the pawn can be promoted
     * @param s Square to check
     * @return true if the pawn can be promoted, false otherwise
     */
    private boolean promotionAvailable(Square s) {
        //If the color is white toY as to be at 7 that means that the piece is on the top of the board(promotion available)
        if(playerColor == PlayerColor.WHITE){
            return s.getPosY() == 7;
        }
        if(playerColor == PlayerColor.BLACK){
            return s.getPosY() == 0;
        }
        return false;
    }

    /**
     * Generate a valid Move for a Pawn
     * @param from source Square
     * @param to destination Square
     * @return a Move if possible, false otherwise.
     */
    @Override
    public Move move(Square from, Square to) {
        int directionY = playerColor == PlayerColor.WHITE ? 1 : -1;
        int deltaX = to.getPosX() - from.getPosX();
        int deltaY = to.getPosY() - from.getPosY();

        if (deltaY == directionY && deltaX == 0) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            if (promotionAvailable(to))
                move.add(MovementType.PROMOTE);
            return new Move(from, to, false, move);
        }

        if (firstMove && deltaY == 2 * directionY && deltaX == 0) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            return new Move(from, to, false, move);
        }

        if(Math.abs(deltaX) == 1 && deltaY == directionY){
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.ATTACK);
            move.add(MovementType.ENPASSANT);

            if (promotionAvailable(to))
                move.add(MovementType.PROMOTE);

            return new Move(from, to, false, move);
        }

        return null;
    }

    /**
     * Return the name of the piece
     * @return String containing the name
     */
    @Override
    public String toString() {
        return "Pawn";
    }
}
