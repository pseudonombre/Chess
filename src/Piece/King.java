package Piece;

import Move.Move;
import java.util.ArrayList;


/**
 * Implements the king.
 */
public class King extends Piece {
    /**
     * holds the list of possible moves to avoid regenerating it on every call to getPossMoves().
     */
    private static final ArrayList<Move> possMoves = new King(true).getPossMoves();

    /**
     * Whether or not the piece has moved. Used for castling.
     */
    private boolean hasMoved = false;

    /**
     * Gets whether or not the piece has moved. Used for castling.
     */
    private boolean getHasMoved() { return hasMoved; }

    /**
     * Creates a new king
     * @param c_isWhite True if the piece is white, false if black.
     */
    public King(boolean c_isWhite) {
        super(c_isWhite, 'K');
    }

    /**
     * Gets a list of all possible  relative to [0,0]. Generates the list only if possMoves has not been filled.
     * @return A list of all possible moves relative to [0,0]
     */
    @Override
    public ArrayList<Move> getPossMoves() {
        if(possMoves != null){ return possMoves; }
        ArrayList<Move> ret = new ArrayList<>();

        ret.addAll(generateDirectionalMoves(new int[] {1,0}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {0,1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,0}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {0,-1}, 1, Move.CaptureStatus.ANY));

        ret.addAll(generateDirectionalMoves(new int[] {1,1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {1,-1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,-1}, 1, Move.CaptureStatus.ANY));

        return ret;
    }

    @Override
    public void move(Move m) {
        hasMoved = true;
    }

}