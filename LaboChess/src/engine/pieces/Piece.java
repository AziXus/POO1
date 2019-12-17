package engine.pieces;

import chess.PlayerColor;
import engine.Board;
import engine.Square;

import java.util.LinkedList;

public abstract class Piece {
    final PlayerColor playerColor;
    int X;
    int Y;
    boolean firstMove = true;

    public Piece(PlayerColor playerColor, int X, int Y) {
        this.playerColor = playerColor;
    }

    public abstract boolean move(Board board, int toX, int toY);

    public abstract boolean attack(Board board, int toX, int toY);
}
