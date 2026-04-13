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
     * Tests whether a move would be possible on an empty, infinite board and finds the list of spaces
     * which would need to be empty for the move to work.
     * @return null if the move is not possible, otherwise an ArrayList with the destination square followed by
     * the squares that must be empty for the move to be possible.
     */
    public abstract Move getMove(int[] destination);

    /**
     * Get a list of all possible moves.
     * Each child class should implement
     * "private static final ArrayList<Move> possMoves;"
     * to simplify future calls to getPossMoves, but this is not enforced.
     * @return a list of all possible moves, with each move's final position at the start of its list followed by
     * a list of spaces which must be empty for the move to be possible
     */
    public abstract ArrayList<Move> getPossMoves();

    /**
     * Generates all squares in the specified direction
     * @param direction the first square relative to [0,0] in the direction of movement
     * @param distance the longest that the piece is able to move. Capped to seven due to board size eight.
     * @return a move in the specified direction the specified distance.
     */
    private static Move generateDirectionalMove(int[] direction, int distance){
        return new Move(new int[2], new ArrayList<int[]>(), Move.CaptureStatus.ANY);
    }

    /**
     * Generates all moves in the specified direction
     * @param direction the first square relative to [0,0] in the direction of movement
     * @param limit the longest that the piece is able to move. Capped to seven due to board size eight.
     * @return a list of all moves in a direction up to and including limit
     */
    private static ArrayList<Move> generateDirectionalMoves(int[] direction, int limit){
        return new ArrayList<Move>();
    }
}
