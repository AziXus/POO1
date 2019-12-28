package engine.pieces;

import chess.PlayerColor;
import engine.Board;
import engine.Square;
import java.lang.Math.*;

public class Queen extends Piece {
    private Piece rookMove = new Rook(this.playerColor);
    private Piece bishopMove = new Bishop(this.playerColor);

    @Override
    public boolean hasPieceOnMouvement(Board board, int toX, int toY) {
        return false;
    }

    public Queen(PlayerColor playerColor) {
        super(playerColor);
    }

    @Override
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        //Queen has the same move as the Bishop and the Rook
        if(rookMove.move(board, fromX, fromY, toX, toY) || bishopMove.move(board, fromX, fromY, toX, toY))
            return true;
        return false;
    }

    @Override
    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
        if(move(board, fromX, fromY, toX, toY))
            return true;
        return false;
    }
}
