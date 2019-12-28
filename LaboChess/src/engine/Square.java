package engine;

import chess.PieceType;
import engine.pieces.Piece;

import java.util.Objects;

public class Square {
    private int posX;
    private int posY;
    private PieceType type;
    private Piece piece;

    public Square(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Square(int posX, int posY, PieceType type, Piece piece) {
        this(posX, posY);
        this.type = type;
        this.piece = piece;
    }

    public PieceType getPieceType(){
        return type;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece p){
        this.piece = p;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o != null && getClass() == o.getClass()
                && ((Square)o).posX == posX && ((Square)o).posY == posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
