import Move.Move;
import Piece.*;

/**
 * Represents a board. holds pieces and manages whether movement is possible
 */
public class Board {
    /**
     * The 2D array holding all the pieces
     */
    private Piece[][] pieces = new Piece[8][8];

    /**
     * Creates a new board with the standard chess starting position
     */
    public Board(){
        this.pieces[0] = new Piece[] {
                new Rook(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Rook(false)
        };
        this.pieces[0] = new Piece[] {
                new Knight(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Knight(false)
        };
        this.pieces[0] = new Piece[] {
                new Bishop(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Bishop(false)
        };
        this.pieces[0] = new Piece[] {
                new Queen(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Queen(false)
        };
        this.pieces[0] = new Piece[] {
                new King(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new King(false)
        };
        this.pieces[0] = new Piece[] {
                new Bishop(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Bishop(false)
        };
        this.pieces[0] = new Piece[] {
                new Knight(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Knight(false)
        };
        this.pieces[0] = new Piece[] {
                new Rook(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Rook(false)
        };
    }

    /**
     * Creates a new board with the same piece configuration
     * @param c_pieces the configuration to put the new board in
     */
    public Board(Piece[][] c_pieces){
        //TODO: implement
    }

    /**
     * @return a string representation of the board
     */
    public String getString() {
        //TODO: implement
        return null;
    }

    /**
     * @param white true if the request is whether the white king is in check, false otherwise
     * @return true if the king of the correct color is in check
     */
    public boolean kingInCheck(boolean white) {
        //TODO: implement
        return false;
    }

    /**
     * Checks whether a move is possible by:
     *     getting if the piece would be able to move there on an empty board (call Piece.Piece.isPossible),
     *     getting the squares that must be empty (call Piece.Piece.isPossible) and checking if they are empty
     *     and the moving player's king is not in check
     * @param move the move to check the possibility of
     * @param from where the move is from
     * @return true if the move is legal according to the rules of chess
     */
    public boolean isPossible(Move move, int[] from) {
        //TODO: implement
        return false;
    }

    /**
     * Makes the given move after checking if it is possible.
     * @param move the move to make
     * @param from where the move is from
     */
    public void makeMove(Move move, int[] from) {
        //TODO: implement
    }
}
