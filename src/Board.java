import Move.Move;
import Piece.*;

import java.util.Arrays;
import java.util.Stack;
import java.util.WeakHashMap;

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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND_WHITE_TEXT = "\u001B[40m\u001B[37m";
    public static final String ANSI_WHITE_BACKGROUND_BLACK_TEXT = "\u001B[47m\u001B[30m";

    /**
     * @return a string representation of the board
     * @param whiteOnBottom true if white starting ranks on bottom, false otherwise
     */
    public String getString(boolean whiteOnBottom) {
        int cellWidth = 3;
        /* It might be nice to add variable cell heights. By cursory inspection, only getRowString() would need changes.
        Until then, cell height is always 1. */
        //int cellHeight = 1;
        StringBuilder ret = new StringBuilder();
        ret.append(ANSI_RESET);
        ret.append(getHeaderString(cellWidth, whiteOnBottom));
        ret.append("\n");
        ret.append(getSpacerString(cellWidth));
        ret.append("\n");
        if(whiteOnBottom){
            for (int i = 7; i >= 0; i--) {
                ret.append(getRowString(i, cellWidth, whiteOnBottom));
                ret.append("\n");
            }
        } else {
            for (int i = 0; i <= 7; i++) {
                ret.append(getRowString(i, cellWidth, whiteOnBottom));
                ret.append("\n");
            }
        }
        ret.append(getSpacerString(cellWidth));
        ret.append("\n");
        ret.append(getHeaderString(cellWidth, whiteOnBottom));
        ret.append("\n");
        return ret.toString();
    }

    /**
     * @param row row to get a string representation of
     * @param cellWidth display width of a cell
     * @param whiteOnBottom true if white starting ranks on bottom, false otherwise
     * @return a string representation of one row
     */
    private String getRowString(int row, int cellWidth, boolean whiteOnBottom) {
        char border = '‖';
        StringBuilder sb = new StringBuilder();
        sb.append(row + 1);
        sb.append(border);
        sb.append(ANSI_WHITE_BACKGROUND_BLACK_TEXT);
        if (whiteOnBottom){
            for (int i = 0; i < 8; i++) {
                sb.append(getCellString(row, i, cellWidth));
            }
        } else {
            for (int i = 7; i > -1; i--) {
                sb.append(getCellString(row, i, cellWidth));
            }
        }

        sb.append(ANSI_RESET);
        sb.append(border);
        sb.append(row + 1);

        return sb.toString();
    }

    /**
     * @param row row of desired square
     * @param column column of desired square
     * @param cellWidth display width of cell
     * @return a string representation of a single square
     */
    private String getCellString(int row, int column, int cellWidth) {
        char sameColorFill = '□';
        char diffColorFill = '■';
        StringBuilder sb = new StringBuilder();
        // set text color based on color of square
        if((row + column) % 2 == 1) {
            sb.append(ANSI_WHITE_BACKGROUND_BLACK_TEXT);
        } else {
            sb.append(ANSI_BLACK_BACKGROUND_WHITE_TEXT);
        }

        // add all spaces if no piece present
        if (pieces[column][row] == null) {
            sb.repeat(' ', cellWidth);
            return sb.toString();
        }

        // place appropriate characters around piece depending on if it is the same color as its square
        if ( pieces[column][row].getIsWhite() == ((row + column) % 2 == 1) ) {
            sb.repeat(sameColorFill, (cellWidth - 1) / 2);
            sb.append(pieces[column][row].getPrintCharacter());
            sb.repeat(sameColorFill, cellWidth / 2);
        } else {
            sb.repeat(diffColorFill, (cellWidth - 1) / 2);
            sb.append(pieces[column][row].getPrintCharacter());
            sb.repeat(diffColorFill, cellWidth / 2);
        }

        return sb.toString();
    }

    /**
     * Gets a header to label the files of the board
     * @param cellWidth Width oc cells in the board
     * @return a header to label the files of the board
     */
    private String getHeaderString(int cellWidth, boolean whiteOnBottom) {
        char border = '‖';
        String headerChars = "ABCDEFGH";
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        sb.append(border);
        if(whiteOnBottom){
            for (int i = 0; i < 8; i++) {
                sb.repeat(' ', (cellWidth - 1) / 2);
                sb.append(headerChars.charAt(i));
                sb.repeat(' ', cellWidth / 2);
            }
        } else {
            for (int i = 7; i > -1; i--) {
                sb.repeat(' ', (cellWidth - 1) / 2);
                sb.append(headerChars.charAt(i));
                sb.repeat(' ', cellWidth / 2);
            }
        }

        sb.append(border);
        return sb.toString();
    }

    /**
     * Get spacer above or below the board
     * @param cellWidth Width of board cells
     * @return spacer string
     */
    private String getSpacerString(int cellWidth) {
        char vertDivider = '=';
        char cornerDivider = '#';
        StringBuilder sb = new StringBuilder();
        sb.append(vertDivider);
        sb.append(cornerDivider);
        for (int i = 0; i < 8; i++) {
            sb.repeat(vertDivider, cellWidth);
        }
        sb.append(ANSI_RESET);
        sb.append(cornerDivider);
        sb.append(vertDivider);
        return sb.toString();
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
    public Move getMove(int[][] coords, boolean isWhite) throws IllegalArgumentException {
        //TODO: Tell caller why move is illegal (probably just throw illegal argument exceptions with different
        // text to pass on to the user
        if(pieces[coords[0][0]][coords[0][1]] == null) {
            throw new IllegalArgumentException("There is not a piece on the starting square.");
        }
        if(pieces[coords[0][0]][coords[0][1]].getIsWhite() != isWhite) {
            throw new IllegalArgumentException("Moving an opponent's piece is not allowed.");
        }
        int[] delta = new int[] {coords[1][0] - coords[0][0], coords[1][1] - coords[0][1]};
        Move ret = pieces[coords[0][0]][coords[0][1]].getMove(delta);
        if(ret == null) { throw new IllegalArgumentException(
                String.format("\"%s\" cannot move in that shape: \"%s\"", //\nIt moves in shapes: \"%s\".",
                        pieces[coords[0][0]][coords[0][1]].getClass(),
                        Arrays.toString(delta))); }
                        //pieces[coords[0][0]][coords[0][1]].getPossMoves())); }
        for(int[] pathSpace : ret.getPath()) {
            if(pieces[pathSpace[0]][pathSpace[1]] != null) {
                throw new IllegalArgumentException("There are pieces in the way of that move.");
            }
        }
        Piece destinationPiece = pieces[coords[1][0]][coords[1][1]];
        if (destinationPiece == null) {
            if(ret.getCaptureStatus() == Move.CaptureStatus.MUST_CAPTURE) {
                throw new IllegalArgumentException("That move must capture, but there is nothing to capture.");
            }
            return ret;
        }
        if (ret.getCaptureStatus() == Move.CaptureStatus.CANNOT_CAPTURE) {
            throw new IllegalArgumentException("That move cannot capture, and there is a piece on the destination square.");
        }
        if (destinationPiece.getIsWhite() == isWhite) {
            throw new IllegalArgumentException("You can't capture your own pieces.");
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
