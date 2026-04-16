package Piece;

import Move.Move;
import java.util.ArrayList;


/**
 * Implements the pawn.
 */
public class Pawn extends Piece {
    /**
     * holds the list of possible moves to avoid regenerating it on every call to getPossMoves().
     */
    private static final ArrayList<Move> possMoves = new Pawn(true).getPossMoves();

    /**
     * Whether or not the piece has moved. Used for jumping two spaces on the first move.
     */
    private boolean hasMoved = false;

    /**
     * Gets whether or not the piece has moved. Used for jumping two spaces on the first move.
     */
    private boolean getHasMoved() { return hasMoved; }

    /**
     * Creates a new pawn
     * @param c_isWhite True if the piece is white, false if black.
     */
    public Pawn(boolean c_isWhite) {
        super(c_isWhite, 'p');
    }

    /**
     * Gets the move corresponding to a given destination relative to [0,0].
     * @param destination The destination of the move relative to [0,0]
     * @return A move corresponding to the given destination if possible, null if no such legal move exists.
     */
    @Override
    public Move getMove(int[] destination) {
        return null;
    }

    /**
     * Gets a list of all possible  relative to [0,0]. Generates the list only if possMoves has not been filled.
     * @return A list of all possible moves relative to [0,0]
     */
    @Override
    public ArrayList<Move> getPossMoves() {
        if(possMoves != null){ return possMoves; }
        ArrayList<Move> ret = new ArrayList<>();

        ret.add(new Move(new int[] {0,1}, null, Move.CaptureStatus.CANNOT_CAPTURE));
        ret.add(new Move(new int[] {1,1}, null, Move.CaptureStatus.MUST_CAPTURE));
        ret.add(new Move(new int[] {-1,1}, null, Move.CaptureStatus.MUST_CAPTURE));
        //TODO: Find way to enforce the following move happening only on first move

        /* hasMoved variable already exists
           Possibly add an optional lambda function to Move that Board can run that must return true?
           Maybe add a lambda that runs after the move for promotion*/
        //ret.add(new Move(new int[] {0,2}, null, Move.CaptureStatus.CANNOT_CAPTURE));

        return ret;
    }

    @Override
    public void move(Move m) {
        hasMoved = true;
    }

}
