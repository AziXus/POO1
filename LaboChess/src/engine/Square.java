package engine;

import engine.pieces.Piece;

import java.util.Objects;

public class Square {
    private int posX;
    private int posY;
//    private Piece piece;

    /**
     * Constructor with the coordinate of the Square
     * @param posX x coordinate
     * @param posY y coordinate
     */
    public Square(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Constructor with another Square
     * @param s reference square
     */
    public Square(Square s) {
        this.posX = s.getPosX();
        this.posY = s.getPosY();
    }

    public Square(int posX, int posY, Piece piece) {
        this(posX, posY);
//        this.piece = piece;
    }

    /**
     * Get x coordinate
     * @return x coordinate
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Get y coordinate
     * @return y coordinate
     */
    public int getPosY() {
        return posY;
    }

//    public Piece getPiece(){
//        return piece;
//    }
//
//    public void setPiece(Piece p){
//        this.piece = p;
//    }

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
