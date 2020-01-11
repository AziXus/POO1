package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

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

    public Queen(PlayerColor playerColor) {
        super(playerColor);
    }

    @Override
    public Move move(Square from, Square to) {
        //Queen has the same move as the Bishop and the Rook
        if(rookMove.move(from, to) != null || bishopMove.move(from, to) != null) {
            ArrayList<MovementType> move = new ArrayList<>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(from, to, false, move);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
