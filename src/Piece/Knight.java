package Piece;

import Move.Move;
import java.util.ArrayList;


/**
 * Implements the knight.
 */
public class Knight extends Piece {
    /**
     * holds the list of possible moves to avoid regenerating it on every call to getPossMoves().
     */
    private static final ArrayList<Move> possMoves = new Knight(true).getPossMoves();

    /**
     * Creates a new knight
     * @param c_isWhite True if the piece is white, false if black.
     */
    public Knight(boolean c_isWhite) {
        super(c_isWhite, 'N');
    }

    /**
     * Gets a list of all possible  relative to [0,0]. Generates the list only if possMoves has not been filled.
     * @return A list of all possible moves relative to [0,0]
     */
    @Override
    public ArrayList<Move> getPossMoves() {
        if(possMoves != null){ return possMoves; }
        ArrayList<Move> ret = new ArrayList<>();

        ret.add(new Move(new int[] {2,1}, null, Move.CaptureStatus.ANY));
        ret.add(new Move(new int[] {2,-1}, null, Move.CaptureStatus.ANY));
        ret.add(new Move(new int[] {-2,1}, null, Move.CaptureStatus.ANY));
        ret.add(new Move(new int[] {-2,-1}, null, Move.CaptureStatus.ANY));

        ret.add(new Move(new int[] {1,2}, null, Move.CaptureStatus.ANY));
        ret.add(new Move(new int[] {1,-2}, null, Move.CaptureStatus.ANY));
        ret.add(new Move(new int[] {-1,2}, null, Move.CaptureStatus.ANY));
        ret.add(new Move(new int[] {-1,-2}, null, Move.CaptureStatus.ANY));

        return ret;
    }

    @Override
    public void move(Move m) {
    }

}