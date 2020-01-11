package engine;

import java.util.ArrayList;

public class Move {
    private Square src;
    private Square dest;
    private boolean canJump;
    private ArrayList<MovementType> type;

    public Move(Square src, Square dest, boolean canJump, ArrayList<MovementType> type){
        this.src = src;
        this.dest = dest;
        this.canJump = canJump;
        this.type = type;
    }

    public Move(int srcX, int srcY, int destX, int destY, boolean canJump, ArrayList<MovementType> type){
        this(new Square(srcX, srcY), new Square(destX, destY), canJump, type);
    }

    public Square getSrc() {
        return src;
    }

    public Square getDest() {
        return dest;
    }

    public int getSrcX() {
        return src.getPosX();
    }

    public int getSrcY() { return src.getPosY(); }

    public int getDestX() {
        return dest.getPosX();
    }

    public int getDestY() {
        return dest.getPosY();
    }

    public boolean isCanJump() {
        return canJump;
    }

    public ArrayList<MovementType> getType() {
        return type;
    }
}
