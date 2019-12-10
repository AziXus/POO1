package engine;

import engine.pieces.Piece;

import java.util.Objects;

public class Square {
    private int posX;
    private int posY;
    //private Piece piece;

    public Square(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

//    public Square(int posX, int posY, Piece piece) {
//        this(posX, posY);
//        this.piece = piece;
//    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
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
