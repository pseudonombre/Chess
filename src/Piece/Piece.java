package Piece;

import Move.Move;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
    public char getPrintCharacter() {
        return printCharacter;
    }

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
     * Creates a copy of the piece
     * @return a copy of the current piece
     */
    public abstract Piece copy();

    /**
     * Gets the move corresponding to a given destination relative to [0,0].
     * @param destination The destination of the move relative to [0,0]
     * @return A move corresponding to the given destination if possible, null if no such legal move exists.
     */
    public ArrayList<Move> getMoves(int[] destination) {
        ArrayList<Move> ret = new ArrayList<>();
        ArrayList<Move> moveList = getPossMoves();
        for(Move m : moveList) {
            if(Arrays.equals(m.getDestination(),destination)) {
                ret.add(m);
            }
        }
        return ret;
    }

    /**
     * Gets a list of all possible  relative to [0,0]. Generates the list only if possMoves has not been filled.
     *      Each child class should implement
     *      "private static final ArrayList<Move.Move> possMoves;"
     *      to simplify future calls to getPossMoves, but this is not enforced.
     * @return A list of all possible moves relative to [0,0]
     */
    public abstract ArrayList<Move> getPossMoves();

    /**
     * Abstract method to update components of a piece's internal state upon moving it.
     * @param m The move being played
     */
    public abstract void move(Move m);

    /**
     * Generates a move in the specified direction
     * @param direction the first square relative to [0,0] in the direction of movement
     * @param distance the longest that the piece is able to move. Capped to seven due to board size eight.
     * @return a move in the specified direction the specified distance.
     */
    public static Move generateDirectionalMove(int[] direction, int distance, Move.CaptureStatus captureStatus){
        if(direction.length != 2) { throw new IllegalArgumentException(); }
        if(distance < 1) { throw new IllegalArgumentException(); }

        int[] currentSquare = direction.clone();
        ArrayList<int[]> path = new ArrayList<>();
        for (int i = 0; i < distance - 1; i++) {
            path.add(currentSquare.clone());
            currentSquare[0] += direction[0];
            currentSquare[1] += direction[1];
        }
        return new Move(currentSquare, path, captureStatus, Move.SpecialMove.NORMAL);
    }

    /**
     * Generates all moves in the specified direction
     * @param direction the first square relative to [0,0] in the direction of movement
     * @param limit the longest that the piece is able to move. Capped to seven due to board size eight.
     * @return a list of all moves in a direction up to and including limit
     */
    public static ArrayList<Move> generateDirectionalMoves(int[] direction, int limit, Move.CaptureStatus captureStatus){
        if(direction.length != 2) { throw new IllegalArgumentException(); }
        if(limit < 1) { throw new IllegalArgumentException(); }

        ArrayList<Move> ret = new ArrayList<Move>();
        for (int i = 1; i <= limit; i++) {
            ret.add(generateDirectionalMove(direction, i, captureStatus));
        }
        return ret;
    }

    /**
     * Reverses the vertical direction of all the moves. Used for getting black pawn moves from white pawn moves.
     * @param moves The moves to reverse
     * @return The reverse of the moves supplied
     */
    public static ArrayList<Move> reverseMoves(ArrayList<Move> moves) {
        ArrayList<Move> ret = new ArrayList<>();
        // for every move
        for(Move move : moves){
            // reverse the destination
            int[] newDestination = move.getDestination().clone();
            newDestination[1] *= -1;
            // reverse the path
            ArrayList<int[]> oldPath = move.getPath();
            ArrayList<int[]> newPath = new ArrayList<>();
            for(int[] space : oldPath) {
                int[] newSpace = space.clone();
                newSpace[1] *= -1;
                newPath.add(newSpace);
            }
            // add the reversed move to the return var
            ret.add(new Move(newDestination, newPath, move.getCaptureStatus(),
                    move.getSpecialMove()));
        }
        return ret;
    }
}
