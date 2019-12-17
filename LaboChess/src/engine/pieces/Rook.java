package engine.pieces;

import chess.PlayerColor;
import engine.Board;
import engine.Square;

public class Rook extends Piece {

    public Rook(PlayerColor playerColor, int X, int Y) {
        super(playerColor, X, Y);
    }

    @Override
    public boolean move(Board board, int toX, int toY) {
        return false;
    }

    @Override
    public boolean attack(Board board, int toX, int toY) {
        return false;
    }
}
