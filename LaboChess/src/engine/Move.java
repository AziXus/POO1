package engine;

import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Class that will contain the different informations of the move from a piece
 */
public class Move {
    private Square src;
    private Square dest;
    private boolean canJump;
    private ArrayList<MovementType> type;

    /**
     * Constructor with the object square for the initial position and the final position
     * @param src object of type square that represents the initial position
     * @param dest object of type square that represents the final position
     * @param canJump boolean that indicates if the move is done by a piece that jumps
     */
    public Move(Square src, Square dest, boolean canJump){
        this.src = src;
        this.dest = dest;
        this.canJump = canJump;
        this.type = new ArrayList<>();
        type.add(MovementType.NONE);
    }

    /**
     * Constructor with the object square for the initial position and the final position
     * @param srcX int that represents the coordinate x of the initial position
     * @param srcY int that represents the coordinate y of the initial position
     * @param destX int that represents the coordinate x of the final position
     * @param destY int that represents the coordinate y of the initial position
     * @param canJump boolean that indicates if the move is done by a piece that jumps
     */
    public Move(int srcX, int srcY, int destX, int destY, boolean canJump){
        this(new Square(srcX, srcY), new Square(destX, destY), canJump);
    }

    /**
     * Constructor with the object square for the initial position and the final position
     * @param src object of type square that represents the initial position
     * @param dest object of type square that represents the final position
     * @param canJump boolean that indicates if the move is done by a piece that jumps
     * @param type Arraylist containing the moves that can by done by moving from src to dest
     * @throws InvalidParameterException if ArrayList of typeMovements is null
     */
    public Move(Square src, Square dest, boolean canJump, ArrayList<MovementType> type){
        this.src = src;
        this.dest = dest;
        this.canJump = canJump;
        if(type == null){
            throw new InvalidParameterException(MessageFormat.format("Un mouvement doit être entrée ! Valeur = {0}", type));
        }
        this.type = type;
    }

    /**
     * Constructor with the position x and y for the initial position and the final position
     * @param srcX int that represents the coordinate x of the initial position
     * @param srcY int that represents the coordinate y of the initial position
     * @param destX int that represents the coordinate x of the final position
     * @param destY int that represents the coordinate y of the initial position
     * @param canJump boolean that indicates if the move is done by a piece that jumps
     * @param type Arraylist containing the moves that can by done by moving from src to dest
     * @throws InvalidParameterException if ArrayList of typeMovements is null
     */
    public Move(int srcX, int srcY, int destX, int destY, boolean canJump, ArrayList<MovementType> type){
        this(new Square(srcX, srcY), new Square(destX, destY), canJump, type);
    }

    /**
     * Get the square of the initial position
     * @return an object of type Square
     */
    public Square getSrc() {
        return src;
    }

    /**
     * Get the square of the final position
     * @return an object of type Square
     */
    public Square getDest() {
        return dest;
    }

    /**
     * Get the position x of the initial square
     * @return int that represents the position x
     */
    public int getSrcX() {
        return src.getPosX();
    }

    /**
     * Get the position y of the initial square
     * @return int that represents the position y
     */
    public int getSrcY() {
        return src.getPosY();
    }

    /**
     * Get the position x of the final square
     * @return int that represents the position x
     */
    public int getDestX() {
        return dest.getPosX();
    }

    /**
     * Get the position y of the final square
     * @return int that represents the position y
     */
    public int getDestY() {
        return dest.getPosY();
    }

    /**
     * Get the boolean that indicates if the move was done by a piece that can jump
     * @return true if the piece could jump false otherwise
     */
    public boolean isCanJump() {
        return canJump;
    }

    /**
     * Get the different that can be done by the piece
     * @return an ArrayList of MovementType that contains the types of move
     */
    public ArrayList<MovementType> getType() {
        return type;
    }
}
