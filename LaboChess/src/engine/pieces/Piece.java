package engine.pieces;

import chess.PlayerColor;
import engine.Square;

import java.util.LinkedList;

public abstract class Piece {
    final PlayerColor playerColor;
    boolean firstMove = true;

    public Piece(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public LinkedList<Square> validMoves(int fromX, int fromY) {
        // Eventuellement throws
        return new LinkedList<>();
    }
}
