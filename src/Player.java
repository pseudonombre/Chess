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
     * Sets the player's name to the input string
     */
    public void setName(String c_name) {name = c_name;}

    /**
     * @return the player's name
     */
    public String getName() {return name;}

    /**
     * Asks the user for input to determine their move. Input will be accepted in the form a2 a4 for moving the piece
     * on a2 to a4. A wider range of input options may be supported later but is currently unplanned.
     * @return an array with the location to move a piece from and the location to move a piece to. E.G. [[6,0],[4,0]]
     * for white moving their a2 pawn to a4.
     */
    public int[][] getMove(){
        System.out.print("Enter input in the format of two coordinates\n" +
                "E.G. \"a2 a4\" or \"a2 to a4\" to move white's a pawn two spaces: ");
        String input = new Scanner(System.in).nextLine();
        input = input.strip();

        int[][] ret = new int[2][2];
        ret[0][0] = input.charAt(0) - 'a';
        ret[0][1] = input.charAt(1) - '1';

        input = input.substring(input.length() - 2);

        ret[1][0] = input.charAt(0) - 'a';
        ret[1][1] = input.charAt(1) - '1';

        return ret;
    }
}
