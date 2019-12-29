package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Square;

import java.util.LinkedList;

public abstract class Piece {
    final PlayerColor playerColor;
    boolean firstMove = true;

    public Piece(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public abstract PieceType getPieceType();

    public abstract boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY);

    public abstract boolean move(Board board, int fromX, int fromY, int toX, int toY);

    public abstract boolean attack(Board board, int fromX, int fromY, int toX, int toY);

    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
