public class Board {
    private Piece[][] pieces = new Piece[8][8];

    /**
     * @return a string representation of the board
     */
    public String getString() {
        return null;
    }

    /**
     * @param white true if the request is whether the white king is in check, false otherwise
     * @return true if the king of the correct color is in check
     */
    public boolean kingInCheck(boolean white) {
        return false;
    }

    /**
     * Checks whether a move is possible by:
     *     getting if the piece would be able to move there on an empty board (call Piece.isPossible),
     *     getting the squares that must be empty (call Piece.isPossible) and checking if they are empty
     *     and the moving player's king is not in check
     * @param from where the move is from
     * @param to where the move is to
     * @return true if the move is legal according to the rules of chess
     */
    public boolean isPossible(int[] from, int[] to) {
        return false;
    }
}
