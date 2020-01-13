package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.move.Move;
import engine.move.MovementType;
import engine.move.Square;

import java.util.ArrayList;

/**
 * Class implementing a Queen that extends Piece
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
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
