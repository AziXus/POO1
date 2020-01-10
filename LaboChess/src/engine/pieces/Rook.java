package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Move;
import engine.MovementType;

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

//    @Override
//    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
//        return false;
//    }

//    Move {
//        //Peut sauter par dessus piece
//        //Type de mouvements : Mouvement, Promotion, Prise
//        //src
//        //dst
//    }

    @Override
    public Move move(int fromX, int fromY, int toX, int toY) {
        if (fromX == toX && fromY == toY)
            return null;

        //Is move on the right or left same Y
        if(fromY == toY) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(fromX, fromY, toX, toY, false, move);
        }
        //Is move up down X is still the same
        if(fromX == toX) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(fromX, fromY, toX, toY, false, move);
        }
        return null;
    }

//    @Override
//    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
//        return move(board, fromX, fromY, toX, toY);
//    }

    @Override
    public String toString() {
        return "Rook";
    }
}
