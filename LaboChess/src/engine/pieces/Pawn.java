package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Move;
import engine.MovementType;

import java.util.ArrayList;

public class Pawn extends Piece {



    public Pawn(PlayerColor playerColor) {
        super(playerColor);
    }
    public String textValue(){
        return toString();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

//    @Override
//    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
//        return false;
//    }

    /**
     * Determine whether the pawn can be promoted
     * @param toX
     * @param toY
     * @return true if the pawn can be promoted, false otherwise
     */
    private boolean promotionAvailable(int toX, int toY) {
        //If the color is white toY as to be at 7 that means that the piece is on the top of the board(promotion available)
        if(playerColor == PlayerColor.WHITE){
            return toY == 7;
        }
        if(playerColor == PlayerColor.BLACK){
            return toY == 0;
        }
        return false;
    }

    @Override
    public Move move(int fromX, int fromY, int toX, int toY) {
        int directionY = playerColor == PlayerColor.WHITE ? 1 : -1;

        if (toY - fromY == directionY && fromX == toX) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            if (promotionAvailable(toX, toY))
                move.add(MovementType.PROMOTE);
            return new Move(fromX, fromY, toX, toY, false, move);
        }

        if (firstMove && toY - fromY == 2 * directionY && fromX == toX) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            return new Move(fromX, fromY, toX, toY, false, move);
        }

        if(Math.abs(toX - fromX) == 1 && toY - fromY == directionY){
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.ATTACK);
            move.add(MovementType.ENPASSANT);

            if (promotionAvailable(toX, toY))
                move.add(MovementType.PROMOTE);

            return new Move(fromX, fromY, toX, toY, false, move);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
