package engine;
import java.util.Objects;

/**
 * Class representing a square on a board by his postion x and y
 */
public class Square {
    private int posX;
    private int posY;

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

    /**
     * Check if two squares are equal
     * @param o the object that as to be compared
     * @return true if equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o == this || o != null && getClass() == o.getClass()
                && ((Square)o).posX == posX && ((Square)o).posY == posY;
    }

    /**
     * Hash a square
     * @return the hash of the square
     */
    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
