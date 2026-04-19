import Move.Move;
import Piece.*;

import java.util.Arrays;
import java.util.Stack;

/**
 * Represents a board. holds pieces and manages whether movement is possible
 */
public class Board {
    /**
     * The 2D array holding all the pieces
     */
    private Piece[][] pieces = new Piece[8][8];

    /**
     * holds white king's position to streamline kingInCheck
     */
    private int[] whiteKingCoords;

    /**
     * holds black king's position to streamline kingInCheck
     */
    private int[] blackKingCoords;

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
        whiteKingCoords = new int[] {4,0};
        blackKingCoords = new int[] {4,7};
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
     * @param isWhite whether the move is supposed to move a white piece or black one
     * @return The Move if the move is possible, null if not
     */
    public Move getMove(int[][] coords, boolean isWhite) {
        if(pieces[coords[0][0]][coords[0][1]].getIsWhite() != isWhite) {
            return null;
        }
        int[] delta = new int[] {coords[1][0] - coords[0][0], coords[1][1] - coords[0][1]};
        Move ret = pieces[coords[0][0]][coords[0][1]].getMove(delta);
        if(ret == null) { return null; }
        for(int[] pathSpace : ret.getPath()) {
            if(pieces[pathSpace[0]][pathSpace[1]] != null) {
                return null;
            }
        }
        return ret;
    }

    /**
     * Makes the given move.
     * @param move the move to make
     * @param from where the move is from
     */
    public void makeMove(Move move, int[] from) {
//        System.out.println(move.toString());
        int[] destination = from.clone();
        destination[0] += move.getDestination()[0];
        destination[1] += move.getDestination()[1];
//        if(pieces[from[0]][from[1]] == null) {
//            System.out.println("nothing in \"from\"");
//        } else {
//            System.out.println("contents of from: " + pieces[from[0]][from[1]].getClass());
//        }
//        if(pieces[destination[0]][destination[1]] == null) {
//            System.out.println("nothing in \"dest\"");
//        } else {
//            System.out.println("contents of dest: " + pieces[destination[0]][destination[1]].getClass());
//        }
        pieces[destination[0]][destination[1]] = pieces[from[0]][from[1]];
        pieces[from[0]][from[1]] = null;
    }
}
