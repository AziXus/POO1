package engine;

import java.util.ArrayList;

public class Move {
    private int srcX;
    private int srcY;
    private int destX;
    private int destY;
    private boolean canJump;
    private ArrayList<MovementType> type;

    public Move(int srcX, int srcY, int destX, int destY, boolean canJump, ArrayList<MovementType> type){
        this.srcX = srcX;
        this.srcY = srcY;
        this.destX = destX;
        this.destY = destY;
        this.canJump = canJump;
        this.type = type;
    }

    public int getSrcX() {
        return srcX;
    }

    public int getSrcY() {
        return srcY;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public boolean isCanJump() {
        return canJump;
    }

    public ArrayList<MovementType> getType() {
        return type;
    }
}
