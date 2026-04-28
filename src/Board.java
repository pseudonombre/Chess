import Move.Move;
import Piece.*;

import java.util.ArrayList;
import java.util.Arrays;

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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(other.pieces[i][j] == null) { continue; }
                pieces[i][j] = other.pieces[i][j].copy();
            }
        }
        whiteKingCoords = other.whiteKingCoords.clone();
        blackKingCoords = other.blackKingCoords.clone();
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
                ret.append(getRowString(i, cellWidth, true));
                ret.append("\n");
            }
        } else {
            for (int i = 0; i <= 7; i++) {
                ret.append(getRowString(i, cellWidth, false));
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
        int[] kingCoords;
        if(white) {
            kingCoords = whiteKingCoords;
        } else {
            kingCoords = blackKingCoords;
        }
        // check pawn spaces
        for(Move move : new Pawn(white).getPossMoves()) {
            if(move.getCaptureStatus() == Move.CaptureStatus.CANNOT_CAPTURE) { continue; }

            // FOR ALL: check that destination is on board before checking if there's a piece there
            if(outOfBounds(move.getDestination()[0] + kingCoords[0]) ||
                    outOfBounds(move.getDestination()[1] + kingCoords[1])) {
                continue;
            }
            Piece destinationPiece = pieces[move.getDestination()[0] + kingCoords[0]][move.getDestination()[1] + kingCoords[1]];
            if(destinationPiece == null) { continue; }
            if(destinationPiece.getClass() == Pawn.class) {
                if(destinationPiece.getIsWhite() != white) {
                    return true;
                }
            }
        }
        // check rook spaces
        moveLoop:
        for(Move move : new Rook(true).getPossMoves()) {
            if(outOfBounds(move.getDestination()[0] + kingCoords[0]) ||
                    outOfBounds(move.getDestination()[1] + kingCoords[1])) {
                continue;
            }
            for(int[] pathSpace : move.getPath()) {
                if(outOfBounds(pathSpace[0] + kingCoords[0]) ||
                        outOfBounds(pathSpace[1] + kingCoords[1])) {
                    continue moveLoop;
                }
                if(pieces[pathSpace[0] + kingCoords[0]][pathSpace[1] + kingCoords[1]] != null) { continue moveLoop; }
            }
            Piece destinationPiece = pieces[move.getDestination()[0] + kingCoords[0]][move.getDestination()[1] + kingCoords[1]];
            if(destinationPiece == null) { continue; }
            if(destinationPiece.getClass() == Rook.class || destinationPiece.getClass() == Queen.class) {
                if(destinationPiece.getIsWhite() != white) {
                    return true;
                }
            }
        }
        // check bishop spaces
        moveLoop:
        for(Move move : new Bishop(true).getPossMoves()) {
            if(outOfBounds(move.getDestination()[0] + kingCoords[0]) ||
                    outOfBounds(move.getDestination()[1] + kingCoords[1])) {
                continue;
            }
            for(int[] pathSpace : move.getPath()) {
                if(outOfBounds(pathSpace[0] + kingCoords[0]) ||
                        outOfBounds(pathSpace[1] + kingCoords[1])) {
                    continue moveLoop;
                }
                if(pieces[pathSpace[0] + kingCoords[0]][pathSpace[1] + kingCoords[1]] != null) { continue moveLoop; }
            }
            Piece destinationPiece = pieces[move.getDestination()[0] + kingCoords[0]][move.getDestination()[1] + kingCoords[1]];
            if(destinationPiece == null) { continue; }
            if(destinationPiece.getClass() == Bishop.class || destinationPiece.getClass() == Queen.class) {
                if(destinationPiece.getIsWhite() != white) {
                    return true;
                }
            }
        }
        // check knight spaces
        for(Move move : new Knight(true).getPossMoves()) {
            if(outOfBounds(move.getDestination()[0] + kingCoords[0]) ||
                    outOfBounds(move.getDestination()[1] + kingCoords[1])) {
                continue;
            }
            Piece destinationPiece = pieces[move.getDestination()[0] + kingCoords[0]][move.getDestination()[1] + kingCoords[1]];
            if(destinationPiece == null) { continue; }
            if(destinationPiece.getClass() == Knight.class) {
                if(destinationPiece.getIsWhite() != white) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return true if the given integer is less than 0 or more than 7
     */
    public boolean outOfBounds(int check) {
        if(check < 0 || check > 7) {
            return true;
        }
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
// PIECE ON FROM SQUARE
        if(pieces[coords[0][0]][coords[0][1]] == null) {
            throw new IllegalArgumentException("There is not a piece on the starting square.");
        }
// PIECE IS PLAYER'S COLOR
        if(pieces[coords[0][0]][coords[0][1]].getIsWhite() != isWhite) {
            throw new IllegalArgumentException("Moving an opponent's piece is not allowed.");
        }
// GET MOVE DATA FROM THE PIECE
        int[] delta = new int[] {coords[1][0] - coords[0][0], coords[1][1] - coords[0][1]};
        ArrayList<Move> moves = pieces[coords[0][0]][coords[0][1]].getMoves(delta);
// PIECE CAN MOVE IN THE RIGHT SHAPE
        if (moves.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("\"%s\" cannot move in that shape: \"%s\"", //\nIt moves in shapes: \"%s\".",
                            pieces[coords[0][0]][coords[0][1]].getClass(),
                            Arrays.toString(delta)));
        }
// FOR EACH POSSIBLE MOVE
        ArrayList<String> exceptions = new ArrayList<>();
        moveListLoop:
        for (int i = 0; i < moves.size(); i++) {
            Move ret = moves.get(i);
// THE MOVE'S PATH IS EMPTY
            for (int[] pathSpace : ret.getPath()) {
                if (pieces[pathSpace[0] + coords[0][0]][pathSpace[1] + coords[0][1]] != null) {
                    exceptions.add("There are pieces in the way of that move.");
                    continue moveListLoop;
                }
            }
// CAPTURE STATUS IS CORRECT
            Piece destinationPiece = pieces[coords[1][0]][coords[1][1]];
            if (destinationPiece == null) {
                if (ret.getCaptureStatus() == Move.CaptureStatus.MUST_CAPTURE) {
                    exceptions.add("That move must capture, but there is nothing to capture.");
                    continue;
                }
            } else {
                if (ret.getCaptureStatus() == Move.CaptureStatus.CANNOT_CAPTURE) {
                    exceptions.add("That move cannot capture, and there is a piece on the destination square.");
                    continue;
                }
                if (destinationPiece.getIsWhite() == isWhite) {
                    exceptions.add("You can't capture your own pieces.");
                    continue;
                }
            }
//        if(ret.getSupplimentaryCondition() != null) {
//            if(! ret.getSupplimentaryCondition().test(coords)){
//                exceptions.add("That move is not possible due to a special rule involving it.\n" +
//                        "Normally, this is attempting en passant, moving a pawn two forwards,\n" +
//                        "or castling when it is not allowed.");
//            }
//        }

// SPECIAL MOVES
            switch (ret.getSpecialMove()) {
                case CASTLE:
                    //just move king pointer and test check
                    King kingHasNotMovedCheck = (King) pieces[coords[0][0]][coords[0][1]];
                    if (kingHasNotMovedCheck.getHasMoved()) {
                        exceptions.add("That king has already moved and cannot castle.");
                        continue;
                    }
                    int[] rookSquare = new int[] {7,coords[0][1]};
                    int[] checkPath = new int[] {-1,0};
                    // if queenside castle
                    if (ret.getDestination()[1] < 0) {
                        rookSquare[0] = 0;
                        checkPath[0] = 1;
                    }
                    Piece rookHasNotMovedCheck = pieces[rookSquare[0]][rookSquare[1]];
                    if(rookHasNotMovedCheck.getClass() == Rook.class){
                        if (((Rook) rookHasNotMovedCheck).getHasMoved()) {
                            exceptions.add("That rook has already moved and cannot castle.");
                            continue;
                        }
                    } else {
                        exceptions.add("The piece in the corner you are castling towards is not a rook.");
                        continue;
                    }
                    // check checkpath and [0,0] are not in check
                    Board checkTestBoard = new Board(this);
                    if (checkTestBoard.kingInCheck(isWhite)) {
                        exceptions.add("You can't castle out of check.");
                        continue;
                    }
                    if(isWhite) {
                        checkTestBoard.whiteKingCoords[0] += (ret.getDestination()[0] / 2);
                    } else {
                        checkTestBoard.blackKingCoords[0] += (ret.getDestination()[0] / 2);
                    }
                    if (checkTestBoard.kingInCheck(isWhite)) {
                        exceptions.add("You can't castle through check.");
                        continue;
                    }

                    break;
                case PAWN_TWO:
                    Pawn pawnHasNotMovedCheck = (Pawn) pieces[coords[0][0]][coords[0][1]];
                    if (pawnHasNotMovedCheck.getHasMoved()) {
                        exceptions.add("That pawn has already moved and cannot move two spaces at once.");
                        continue;
                    }
                    break;
                case EN_PASSANT:
                    Pawn enPassantCheck = (Pawn) pieces[coords[0][0] + ret.getDestination()[0]][coords[0][1]];
                    if ( (! enPassantCheck.getJustMovedTwo()) || enPassantCheck.getIsWhite() == isWhite) {
                        exceptions.add("En passant is not possible here");
                        continue;
                    }
                    break;
            }
// NOT ENDING IN CHECK
            Board checkTestBoard = new Board(this);
            checkTestBoard.makeMove(ret, coords[0]);
            if (checkTestBoard.kingInCheck(isWhite)) {
                exceptions.add("You can't end your turn in check.");
                continue;
            }
            if(exceptions.size() < (i + 1)) {
                return ret;
            }
        }
        // RETURN MOVE
        throw new IllegalArgumentException(String.valueOf(exceptions));
    }

    /**
     * Makes the given move.
     * @param move the move to make
     * @param from where the move is from
     * @return wether or not the move results in a promotion
     */
    public boolean makeMove(Move move, int[] from) {
        int[] destination = from.clone();
        if(pieces[from[0]][from[1]].getClass().equals(King.class)) {
            if(pieces[from[0]][from[1]].getIsWhite()) {
                whiteKingCoords[0] += move.getDestination()[0];
                whiteKingCoords[1] += move.getDestination()[1];
            } else {
                blackKingCoords[0] += move.getDestination()[0];
                blackKingCoords[1] += move.getDestination()[1];
            }
        }
        destination[0] += move.getDestination()[0];
        destination[1] += move.getDestination()[1];
        pieces[destination[0]][destination[1]] = pieces[from[0]][from[1]].copy();
        pieces[from[0]][from[1]] = null;

        switch (move.getSpecialMove()) {
            case CASTLE:
                int[] rookFromSquare = new int[] {7,from[1]};
                int[] rookToSquare = new int[] {5,from[1]};
                // if queenside castle
                if (move.getDestination()[1] < 0) {
                    rookFromSquare[0] = 0;
                    rookToSquare[0] = 3;
                }
                pieces[rookToSquare[0]][rookToSquare[1]] = pieces[rookFromSquare[0]][rookFromSquare[1]].copy();
                pieces[rookFromSquare[0]][rookFromSquare[1]] = null;
                break;
            case EN_PASSANT:
                pieces[from[0] + move.getDestination()[0]][from[1]] = null;
                break;
        }
        if(pieces[destination[0]][destination[1]].getClass() == Pawn.class){
            if(destination[1] == 7 || destination[1] == 0) {
                return true;
            }
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i == destination[0] && j == destination[1]) { continue; }
                Piece piece = pieces[i][j];
                if(piece == null) { continue; }
                if(piece.getClass() == Pawn.class) {
                    ((Pawn) piece).setJustMovedTwoFalse();
                }
            }
        }
        pieces[destination[0]][destination[1]].move(move);
        return false;
    }

    public boolean hasLegalMoves(boolean isWhite) {
        Board copy = new Board(this);
        // ew
        // what the algorithm
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(copy.pieces[i][j] != null) {
                    if(copy.pieces[i][j].getIsWhite() == isWhite) {
                        for(Move move : copy.pieces[i][j].getPossMoves()) {
                            // no catch because an exception simply means the move is not possible and we can
                            // just move on
                            try {
                                if (copy.getMove(new int[][]{{i, j}, {i + move.getDestination()[0],
                                        j + move.getDestination()[1]}}, isWhite) != null) {
                                    return true;
                                }
                            } catch (Exception _) {}
                        }
                    }
                }
            }
        }
        return false;
    }

    void promotePiece(int[] destination, char desiredPiece) {
        if(destination.length != 2) { throw new IllegalArgumentException(); }
        boolean isWhite = pieces[destination[0]][destination[1]].getIsWhite();
        // to set hasMoved to true
        Move defaultMove = new Move(new int[] {0,0}, null, Move.CaptureStatus.ANY, Move.SpecialMove.NORMAL);
        switch(desiredPiece) {
            case 'q':
                pieces[destination[0]][destination[1]] = new Queen(isWhite);
                pieces[destination[0]][destination[1]].move(defaultMove);
                break;
            case 'n':
                pieces[destination[0]][destination[1]] = new Knight(isWhite);
                pieces[destination[0]][destination[1]].move(defaultMove);
                break;
            case 'r':
                pieces[destination[0]][destination[1]] = new Rook(isWhite);
                pieces[destination[0]][destination[1]].move(defaultMove);
                break;
            case 'b':
                pieces[destination[0]][destination[1]] = new Bishop(isWhite);
                pieces[destination[0]][destination[1]].move(defaultMove);
                break;
        }
    }
}
