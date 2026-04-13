import java.util.ArrayList;


/**
 * Implements the rook.
 */
public class Rook extends Piece {
    /**
     * holds the list of possible moves to avoid regenerating it on every call to getPossMoves().
     */
    private static final ArrayList<Move> possMoves = new Rook(true).getPossMoves();

    /**
     * Creates a new rook
     * @param c_isWhite True if the piece is white, false if black.
     */
    public Rook(boolean c_isWhite) {
        super(c_isWhite, 'R');
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
        return null;
    }

}
