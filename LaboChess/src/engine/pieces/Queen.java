package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Move;
import engine.MovementType;
import engine.Square;
import java.lang.Math.*;
import java.util.ArrayList;

public class Queen extends Piece {
    private Piece rookMove = new Rook(this.playerColor);
    private Piece bishopMove = new Bishop(this.playerColor);

    public String textValue(){
        return toString();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }

    @Override
    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    public Queen(PlayerColor playerColor) {
        super(playerColor);
    }

    @Override
    public Move move(Board board, int fromX, int fromY, int toX, int toY) {
        //Queen has the same move as the Bishop and the Rook
        if(rookMove.move(board, fromX, fromY, toX, toY) != null || bishopMove.move(board, fromX, fromY, toX, toY) != null) {
            ArrayList<MovementType> move = new ArrayList<MovementType>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(fromX, fromY, toX, toY, false, move);
        }
        return null;
    }

//    @Override
//    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
//        if(move(board, fromX, fromY, toX, toY))
//            return true;
//        return false;
//    }

    @Override
    public String toString() {
        return "Queen";
    }
}
