package Piece;

import Move.Move;
import java.util.ArrayList;

/**
 * Abstract piece class to model individual piece classes after. Importantly, pieces are not responsible for their
 * positions.
 */
public abstract class Piece {
    /**
     * True if the piece is white, false if black.
     */
    private final boolean isWhite;
    /**
     * @return True if the piece is white, false if black.
     */
    public boolean getIsWhite(){return isWhite;}
    /**
     * The character to print for this piece when displaying the board
     */
    private final char printCharacter;
    /**
     * @return The character to print for this piece when displaying the board
     */
    public char getPrintCharacter(){return printCharacter;}

    /**
     * Creates a new piece
     * @param c_isWhite True if the piece is white, false if black.
     * @param c_printCharacter The character to print for this piece when displaying the board
     */
    public Piece(boolean c_isWhite, char c_printCharacter) {
        isWhite = c_isWhite;
        printCharacter = c_printCharacter;
    }

    /**
     * Gets the move corresponding to a given destination relative to [0,0].
     * @param destination The destination of the move relative to [0,0]
     * @return A move corresponding to the given destination if possible, null if no such legal move exists.
     */
    public abstract Move getMove(int[] destination);

    /**
     * Gets a list of all possible  relative to [0,0]. Generates the list only if possMoves has not been filled.
     *      * Each child class should implement
     *      * "private static final ArrayList<Move.Move> possMoves;"
     *      * to simplify future calls to getPossMoves, but this is not enforced.
     * @return A list of all possible moves relative to [0,0]
     */
    public abstract ArrayList<Move> getPossMoves();

    /**
     * Abstract method to update components of a piece's internal state upon moving it.
     * @param m The move being played
     */
    public abstract void move(Move m);

    /**
     * Generates all squares in the specified direction
     * @param direction the first square relative to [0,0] in the direction of movement
     * @param distance the longest that the piece is able to move. Capped to seven due to board size eight.
     * @return a move in the specified direction the specified distance.
     */
    public static Move generateDirectionalMove(int[] direction, int distance){
        return new Move(new int[2], new ArrayList<int[]>(), Move.CaptureStatus.ANY);
    }

    /**
     * Generates all moves in the specified direction
     * @param direction the first square relative to [0,0] in the direction of movement
     * @param limit the longest that the piece is able to move. Capped to seven due to board size eight.
     * @return a list of all moves in a direction up to and including limit
     */
    public static ArrayList<Move> generateDirectionalMoves(int[] direction, int limit){
        return new ArrayList<Move>();
    }
}
