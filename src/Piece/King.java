package Piece;

import Move.Move;

import java.lang.reflect.Array;
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
    public boolean getHasMoved() { return hasMoved; }

    /**
     * Creates a new king
     * @param c_isWhite True if the piece is white, false if black.
     */
    public King(boolean c_isWhite) {
        super(c_isWhite, 'K');
    }

    @Override
    public King copy() {
        King ret = new King(getIsWhite());
        ret.hasMoved = hasMoved;
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

        ret.addAll(generateDirectionalMoves(new int[] {1,0}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {0,1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,0}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {0,-1}, 1, Move.CaptureStatus.ANY));

        ret.addAll(generateDirectionalMoves(new int[] {1,1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {1,-1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,1}, 1, Move.CaptureStatus.ANY));
        ret.addAll(generateDirectionalMoves(new int[] {-1,-1}, 1, Move.CaptureStatus.ANY));

        // Kingside castle
        ArrayList<int[]> kingsidePath = new ArrayList<>();
        kingsidePath.add(new int[] {-1, 0});
        kingsidePath.add(new int[] {-2, 0});
        ret.add(new Move(new int[] {-2, 0}, kingsidePath, Move.CaptureStatus.CANNOT_CAPTURE, Move.SpecialMove.CASTLE));
        // Queenside castle
        ArrayList<int[]> queensidePath = new ArrayList<>();
        queensidePath.add(new int[] {1,0});
        queensidePath.add(new int[] {2,0});
        queensidePath.add(new int[] {3, 0});
        ret.add(new Move(new int[] {2,0}, queensidePath, Move.CaptureStatus.CANNOT_CAPTURE, Move.SpecialMove.CASTLE));

        return ret;
    }

    @Override
    public void move(Move m) {
        hasMoved = true;
    }

}