package engine.pieces;

import chess.PlayerColor;
import engine.Board;
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
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        // Move only possible on a null dest
        if (board.getPiece(toX, toY) != null) {
            return false;
        }

        if (playerColor == PlayerColor.WHITE) {
            if (toY - fromY == 1) {
                return true;
            }

            if (firstMove && toY - fromY == 2) {
                firstMove = false;
                return board.getPiece(fromX, fromY + 1) == null;
            }
        } else if (playerColor == PlayerColor.BLACK) {
            if (fromY - toY == 1) {
                return true;
            }

            if (firstMove && fromY - toY == 2) {
                firstMove = false;
                return board.getPiece(fromX, fromY - 1) == null;
            }
        }

        return false;
    }

    @Override
    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    //    @Override
//    public LinkedList<Square> validMoves(int fromX, int fromY) {
//        LinkedList<Square> moves = new LinkedList<>();
//
//        int direction = playerColor == PlayerColor.WHITE ? 1 : -1;
//
//        int newY = fromY + direction;
//
//        if (newY <= 7 && newY >= 0) {
//            moves.add(new Square(fromX, newY));
//        }
//
//        if (firstMove) {
//            moves.add(new Square( fromX, newY + direction));
//        }
//
//        return moves;
//    }
}
