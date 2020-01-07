package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.Move;
import engine.MovementType;
import engine.Square;

import java.util.ArrayList;

public class King extends Piece {
    public King(PlayerColor playerColor) {
        super(playerColor);
    }

    public String textValue(){
        return toString();
    }

//    public boolean isInDanger(Board board, int posX, int posY) {
//        for (int x = 0; x < 8; x++) {
//            for (int y = 0; y < 8; y++) {
//                Piece p = board.getPiece(x, y);
//                //TODO the king cannot be checked here because it will make an infinite loop of check if king is in danger
//                if(p != null && p.getClass() == King.class){
//                    continue;
//                }
//                //TODO the flag of the différent pieces is update so after a king move impossible to move the pawn by 2 square
//                if (p != null && p.attack(board, x, y, posX, posY) && p.getPlayerColor() != this.playerColor)
//                    return true;
//            }
//        }
//
//        return false;
//    }

//    private boolean smallCastling(Board board, int fromX, int fromY, int toX, int toY){
//        //Verify that the castling can be done
//        if(isInDanger(board,fromX + 1, fromY + 1)){
//            return false;
//        }
//        if(isInDanger(board,toX, toY)){
//            return false;
//        }
//        //if the 2 tests conclude the king is not in danger by making the castling so mouvement of the rook and the king
//        return true;
//    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public boolean hasPieceOnMouvement(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public Move move(Board board, int fromX, int fromY, int toX, int toY) {
        int deltaX = Math.abs(toX - fromX);
        int deltaY = Math.abs(toY - fromY);

        if ((deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 1)) {
            ArrayList<MovementType> move = new ArrayList<MovementType>();
            move.add(MovementType.MOVE);
            move.add(MovementType.ATTACK);
            return new Move(fromX, fromY, toX, toY, false, move);
        }

        if ((toX - fromX == 2 && deltaY == 0)) {
            ArrayList<MovementType> move = new ArrayList<MovementType>();
            move.add(MovementType.SMALLCATSLING);
            return new Move(fromX, fromY, toX, toY, false, move);
        }

        if ((toX - fromX == -2 && deltaY == 0)) {
            ArrayList<MovementType> move = new ArrayList<MovementType>();
            move.add(MovementType.BIGCASTLING);
            return new Move(fromX, fromY, toX, toY, false, move);
        }
        return null;
    }

//    @Override
//    public boolean attack(Board board, int fromX, int fromY, int toX, int toY) {
//        return move(board, fromX, fromY, toX, toY);
//    }

    @Override
    public String toString() {
        return "King";
    }
}
