package engine.pieces;

import chess.PlayerColor;
import engine.Square;

import java.util.LinkedList;

public class Pawn extends Piece {

    public Pawn(PlayerColor playerColor) {
        super(playerColor);
    }

    public boolean promotionAvailable() {
        return true;
    }

    @Override
    public LinkedList<Square> validMoves(int fromX, int fromY) {
        LinkedList<Square> moves = new LinkedList<>();

        int direction = playerColor == PlayerColor.WHITE ? 1 : -1;

        int newY = fromY + direction;

        if (newY <= 7 && newY >= 0) {
            moves.add(new Square(fromX, newY));
        }

        if (firstMove) {
            moves.add(new Square( fromX, newY + direction));
        }

        return moves;
    }
}
