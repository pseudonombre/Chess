package Piece;

import Move.Move;
import java.util.ArrayList;

import static java.lang.Math.abs;


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
    public boolean getHasMoved() { return hasMoved; }

    /**
     * Whether or not the piece just moved two spaces. used for en passant.
     */
    private boolean justMovedTwo = false;

    /**
     * Gets whether or not the piece just moved two spaces. used for en passant.
     */
    public boolean getJustMovedTwo() { return justMovedTwo; }

    /**
     * Sets justMovedTwo to false. Used when other pieces move.
     */
    public void setJustMovedTwoFalse() {
        if(justMovedTwo)
            justMovedTwo = false;
    }

    /**
     * Creates a new pawn
     * @param c_isWhite True if the piece is white, false if black.
     */
    public Pawn(boolean c_isWhite) {
        super(c_isWhite, 'p');
    }

    /**
     * Creates a copy of the Pawn
     * @return a copy of the current Pawn
     */
    @Override
    public Pawn copy() {
        Pawn ret = new Pawn(getIsWhite());
        ret.hasMoved = hasMoved;
        ret.justMovedTwo = justMovedTwo;
        return ret;
    }

    /**
     * Gets a list of all possible  relative to [0,0]. Generates the list only if possMoves has not been filled.
     * @return A list of all possible moves relative to [0,0]
     */
    @Override
    public ArrayList<Move> getPossMoves() {
        if(possMoves != null){
            if(getIsWhite()) {
                return possMoves;
            } else {
                return reverseMoves(possMoves);
            }
        }
        ArrayList<Move> ret = new ArrayList<>();

        //Normal moves
        ret.add(new Move(new int[]{0, 1}, null, Move.CaptureStatus.CANNOT_CAPTURE, Move.SpecialMove.NORMAL));
        ret.add(new Move(new int[]{1, 1}, null, Move.CaptureStatus.MUST_CAPTURE, Move.SpecialMove.NORMAL));
        ret.add(new Move(new int[]{-1, 1}, null, Move.CaptureStatus.MUST_CAPTURE, Move.SpecialMove.NORMAL));

        //Move two
        ret.add(new Move(new int[]{0, 2}, null, Move.CaptureStatus.CANNOT_CAPTURE, Move.SpecialMove.PAWN_TWO));

        //En passant
        ret.add(new Move(new int[]{1, 1}, null, Move.CaptureStatus.CANNOT_CAPTURE, Move.SpecialMove.EN_PASSANT));
        ret.add(new Move(new int[]{-1, 1}, null, Move.CaptureStatus.CANNOT_CAPTURE, Move.SpecialMove.EN_PASSANT));

        /*
        The moves for black pieces should be reversed, but because this should only run once at the start and
        quit early every other time, this is hardcoded to only give the moves for white pieces
         */
        return ret;
    }

    /**
     * Update hasMoved and justMovedTwo
     * @param m The move being played
     */
    @Override
    public void move(Move m) {
        hasMoved = true;
        if(abs(m.getDestination()[1]) == 2) {
            justMovedTwo = true;
        } else {
            // this statement required because the justMovedTwo statuses of all pawns on the board except the one
            // that just moved are set to false every move. This will only be relevant when a pawn moves again after
            // moving two, but still important
            justMovedTwo = false;
        }
    }

}
