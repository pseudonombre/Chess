import java.util.Scanner;

/**
 * Represents one player in the game. Gets the player's input.
 */
public class Player {
    /**
     * The player's name
     */
    private String name;

    /**
     * @return the player's name
     */
    public String getName() {return name;}

    /**
     * Creates a new player
     */
    public Player(String c_name) {
        name = c_name;
    }

    /**
     * Asks the user for input to determine their move. Input will be accepted in the form a2 a4 for moving the piece
     * on a2 to a4. A wider range of input options may be supported later but is currently unplanned.
     * @return an array with the location to move a piece from and the location to move a piece to. E.G. [[6,0],[4,0]]
     * for white moving their a2 pawn to a4.
     */
    public int[][] getMove(){
        System.out.print(name + ": enter input in the format of two coordinates\n" +
                "E.G. \"a2 a4\" or \"a2 to a4\" to move white's a pawn two spaces: ");
        String input = new Scanner(System.in).nextLine();
        input = input.strip().toLowerCase();
        switch(input) {
            case "offer draw":
                return new int[][] {{otherInputs.OFFER_DRAW.ordinal()}};
            case "resign":
                return new int[][] {{otherInputs.RESIGN.ordinal()}};
            case "undo":
                return new int[][] {{otherInputs.UNDO.ordinal()}};
            case "redo":
                return new int[][] {{otherInputs.REDO.ordinal()}};
        }

        int[][] ret = new int[2][2];
        ret[0][0] = getFile(input.charAt(0));
        ret[0][1] = getRank(input.charAt(1));

        input = input.substring(input.length() - 2);

        ret[1][0] = getFile(input.charAt(0));
        ret[1][1] = getRank(input.charAt(1));

        for(int[] a : ret) {
            for(int i : a) {
                if(i == -1) { return null; }
            }
        }

        return ret;
    }

    /**
     * gets file (column) from character
     * @param c the name of the file
     * @return the file number referred to by the character
     */
    private int getFile(char c) {
        return "abcdefgh".indexOf(c);
    }

    /**
     * gets rank (row) from character
     * @param c the number of the rank
     * @return the rank number referred to by the character
     */
    private int getRank(char c) {
        return "12345678".indexOf(c);
    }

    /**
     * Inputs other than normal moves
     */
    public enum otherInputs {
        OFFER_DRAW,
        RESIGN,
        UNDO,
        REDO
    }
}
