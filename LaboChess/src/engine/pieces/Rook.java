package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Square;

public class Rook extends Piece {

    public Rook(PlayerColor playerColor) {
        super(playerColor);
    }

    public String textValue(){
        return toString();
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        //Is move on the right or left same Y
        if(fromY == toY) {
            //Verify that a piece is not on the path of the movement if there is one the move is invalid
            for (int i = fromX + 1; i < toX; i++)
                if (board.getPiece(i, fromY) != null)
                    return false;
            return true;
        }
        //Is move up down X is still the same
        if(fromX == toX) {
            //Verify that a piece is not on the path of the movement if there is one the move is invalid
            for (int i = fromY + 1; i < toY; i++)
                if (board.getPiece(fromX, i) != null)
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
        return move(board, fromX, fromY, toX, toY);
    }

    @Override
    public String toString() {
        return "Rook";
    }
}
