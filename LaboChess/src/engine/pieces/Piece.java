package engine.pieces;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;
import engine.Move;
import engine.Square;

/**
 * Abstract class for the implementation of the different pieces of a chess Game
 */
public abstract class Piece implements ChessView.UserChoice {
    final PlayerColor playerColor;
    boolean firstMove = true;

    /**
     * Constructor of class Piece that set the PlayerColor
     * @param playerColor Color of the piece
     */
    public Piece(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Return the name of the piece as a String
     * @return name of the piece
     */
    public String textValue() {
        return this.toString();
    }

    /**
     * Return the type of the piece
     * @return PieceType representing the type of the piece
     */
    public abstract PieceType getPieceType();

    /**
     * Generate a Move that contains the possible MovementTypes and whether the piece can jump
     * @param from source Square
     * @param to destination Square
     * @return a complete Move if possible, null otherwise
     */
    public abstract Move move(Square from, Square to);

    /**
     * Set the firstMove boolean to false
     */
    public void hasMoved() {
        if(firstMove) {
            this.firstMove = false;
        }
    }

    /**
     * Return the color of the piece
     * @return PlayerColor that represents the color of the piece
     */
    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    /**
     * Return whether the piece has moved
     * @return true if the piece has not moved, false otherwise
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public abstract String toString();
}
