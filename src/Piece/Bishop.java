package Piece;

import Move.Move;
import java.util.ArrayList;


/**
 * Implements the bishop.
 */
public class Bishop extends Piece {
    /**
     * holds the list of possible moves to avoid regenerating it on every call to getPossMoves().
     */
    private static final ArrayList<Move> possMoves = new Bishop(true).getPossMoves();

    /**
     * Creates a new bishop
     * @param c_isWhite True if the piece is white, false if black.
     */
    public Bishop(boolean c_isWhite) {
        super(c_isWhite, 'B');
    }

    /**
     * Creates a copy of the Bishop
     * @return a copy of the current Bishop
     */
    @Override
    public Bishop copy() {
        Bishop ret = new Bishop(getIsWhite());
        return ret;
    }

    /**
     * Gets a list of all possible  relative to [0,0]. Generates the list only if possMoves has not been filled.
     * @return A list of all possible moves relative to [0,0]
     */
    @Override
    public ArrayList<Move> getPossMoves() {
        if(possMoves != null){ return possMoves; }
        ArrayList<Move> ret = new ArrayList<>();

        ret.addAll(generateDirectionalMoves(new int[] {1,1}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {1,-1}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,1}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,-1}, 7, Move.CaptureStatus.ANY));

        return ret;
    }

    /**
     * Updates internal states of pieces; Bishop has no internal states to update
     * @param m The move being played
     */
    @Override
    public void move(Move m) {
    }

}