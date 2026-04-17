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
        this.pieces[1] = new Piece[] {
                new Knight(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Knight(false)
        };
        this.pieces[2] = new Piece[] {
                new Bishop(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Bishop(false)
        };
        this.pieces[3] = new Piece[] {
                new Queen(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Queen(false)
        };
        this.pieces[4] = new Piece[] {
                new King(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new King(false)
        };
        this.pieces[5] = new Piece[] {
                new Bishop(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Bishop(false)
        };
        this.pieces[6] = new Piece[] {
                new Knight(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Knight(false)
        };
        this.pieces[7] = new Piece[] {
                new Rook(true),
                new Pawn(true),
                null, null, null, null,
                new Pawn(false),
                new Rook(false)
        };
    }

    /**
     * Creates a new board with the same piece configuration
     * @param other the board to copy
     */
    public Board(Board other){
        pieces = other.pieces;
    }

    /**
     * @return a string representation of the board
     */
    public String getString() {
        StringBuilder[] sBArray = new StringBuilder[8];
        for (int i = 0; i < 8; i++) {
            sBArray[i] = new StringBuilder();
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] == null) {
                    sBArray[j].append(" ");
                }
                else {
                    sBArray[j].append(pieces[i][j].getPrintCharacter());
                }
            }
        }
        StringBuilder fillSpaces = new StringBuilder();
        fillSpaces.append("=========================\n");
        for (int i = 0; i < 8; i++) {
            fillSpaces.append(sBArray[i].toString());
            fillSpaces.append("\n");
        }
        fillSpaces.append("=========================\n");
        return fillSpaces.toString();
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
     *     getting if the piece would be able to move there on an empty board (call Piece.getMove),
     *     getting the squares that must be empty (call Piece.getMove) and checking if they are empty
     *     and the moving player's king is not in check
     * @param coords an array of coordinates. coords[0] is the beginning position and coords[1] is the ending position.
     * @return The Move if the move is possible, null if not
     */
    public Move getMove(int[][] coords) {
        int[] delta = new int[] {coords[1][0] - coords[0][0], coords[1][1] - coords[0][1]};
        return pieces[coords[0][0]][coords[0][1]].getMove(delta);
    }

    /**
     * Makes the given move.
     * @param move the move to make
     * @param from where the move is from
     */
    public void makeMove(Move move, int[] from) {
        //TODO: implement
    }
}
