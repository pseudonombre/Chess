package Piece;

import Move.Move;
import java.util.ArrayList;


/**
 * Implements the queen.
 */
public class Queen extends Piece {
    /**
     * holds the list of possible moves to avoid regenerating it on every call to getPossMoves().
     */
    private static final ArrayList<Move> possMoves = new Queen(true).getPossMoves();

    /**
     * Creates a new queen
     * @param c_isWhite True if the piece is white, false if black.
     */
    public Queen(boolean c_isWhite) {
        super(c_isWhite, 'Q');
    }

    @Override
    public Queen copy() {
        Queen ret = new Queen(getIsWhite());
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

        ret.addAll(generateDirectionalMoves(new int[] {1,0}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {0,1}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,0}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {0,-1}, 7, Move.CaptureStatus.ANY));

        ret.addAll(generateDirectionalMoves(new int[] {1,1}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {1,-1}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,1}, 7, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,-1}, 7, Move.CaptureStatus.ANY));

        return ret;
    }

    @Override
    public void move(Move m) {
    }

}