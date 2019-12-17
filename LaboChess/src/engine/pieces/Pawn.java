package engine.pieces;

import chess.PlayerColor;
import engine.Board;
import engine.Square;

import java.util.LinkedList;

public class Pawn extends Piece {

    public Pawn(PlayerColor playerColor, int X, int Y) {
        super(playerColor, X, Y);
    }

    public boolean promotionAvailable() {
        return true;
    }

    @Override
    public boolean move(Board board, int toX, int toY) {
        // Move only possible on a null dest
        if (board.getPiece(toX, toY) != null) {
            return false;
        }

        if (playerColor == PlayerColor.WHITE) {
            if (toY - currentY == 1) {
                return true;
            }

            if (firstMove && toY - currentY == 2) {
                return board.getPiece(currentX, currentY + 1) == null;
            }
        } else if (playerColor == PlayerColor.BLACK) {
            if (currentY - toY == 1) {
                return true;
            }

            if (firstMove && currentY - toY == 2) {
                return board.getPiece(currentX, currentY - 1) == null;
            }
        }

        return false;
    }

    @Override
    public boolean attack(Board board, int toX, int toY) {
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
