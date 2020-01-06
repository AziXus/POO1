package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Square;

public class King extends Piece {
    public King(PlayerColor playerColor) {
        super(playerColor);
    }

    public String textValue(){
        return toString();
    }

    public boolean isInDanger(Board board, int posX, int posY) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = board.getPiece(x, y);

                if(p != null && p.getClass() == King.class){
                    continue;
                }

                if (p != null && p.attack(board, x, y, posX, posY) && p.getPlayerColor() != this.playerColor)
                    return true;
            }
        }

        return false;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean move(Board board, int fromX, int fromY, int toX, int toY) {
        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if(isInDanger(board, toX, toY)){
            return false;
        }

        if ((deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 1)) {
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
        return "King";
    }
}
