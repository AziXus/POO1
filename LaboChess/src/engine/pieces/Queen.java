package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

public class Queen extends Piece {
    // A queen is a combination of a rook and a bishop
    private Piece rookMove = new Rook(this.playerColor);
    private Piece bishopMove = new Bishop(this.playerColor);

    /**
     * Constructor of the Queen
     * @param playerColor color of the player that owns the piece
     */
    public Queen(PlayerColor playerColor) {
        super(playerColor);
    }

    /**
     * Override that returns a QUEEN;
     * @return type of the piece
     */
    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }

    /**
     * Generate a valid Move for a Queen
     * @param from source Square
     * @param to destination Square
     * @return a Move if possible, false otherwise.
     */
    @Override
    public Move move(Square from, Square to) {
        ArrayList<MovementType> move = new ArrayList<>();
        //Queen has the same move as the Bishop and the Rook
        if(!rookMove.move(from, to).getType().contains(MovementType.NONE) || !bishopMove.move(from, to).getType().contains(MovementType.NONE)) {
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
        return "Queen";
    }
}
