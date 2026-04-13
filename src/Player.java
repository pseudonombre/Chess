public class Player {
    private String name;
    public void setName(String c_name) {name = c_name;}
    public String getName() {return name;}

    /**
     * Asks the user for input to determine their move. Input will be accepted in the form a2 a4 for moving the piece
     * on a2 to a4. A wider range of input options may be supported later but is currently unplanned.
     * @return an array with the location to move a piece from and the location to move a piece to. E.G. [[6,0],[4,0]]
     * for white moving their a2 pawn to a4.
     */
    public int[][] getMove(){
        return new int[0][];
    }
}
