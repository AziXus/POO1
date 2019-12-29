package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Square;

import java.util.LinkedList;

public class Pawn extends Piece {

    public Pawn(PlayerColor playerColor) {
        super(playerColor);
    }
    public String textValue(){
        return "Rook";
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
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
        if (playerColor == PlayerColor.WHITE) {
            return Math.abs(toX - fromX) == 1 && toY - fromY == 1;
        } else if (playerColor == PlayerColor.BLACK) {
            return Math.abs(toX - fromX) == 1 && fromY - toY == 1;
        }

        return false;
    }
}
