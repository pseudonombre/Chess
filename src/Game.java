import java.util.Stack;

/**
 * Models a game of chess. Holds current and past board states and allows the players' input to be applied
 * to the current board.
 */
public class Game {
    /**
     * The two players to ask for input
     */
    private Player[] players;
    /**
     * Past boards that can be rolled back to
     */
    private Stack<Board> undoStack;
    /**
     * Future boards present if undo was used and no moves have been made since
     */
    private Stack<Board> redoStack;
    /**
     * True if white moves next, false otherwise
     */
    private boolean whiteToMove;

    /**
     * Creates a new game
     */
    public Game() {
        players = new Player[2];
    }

    /**
     * Plays a game. Gets player's moves and plays them in a loop until the game ends
     */
    public void play() {
        //TODO: implement
        while(true) {
            //get input
            //test if it is legal
            //play it
            whiteToMove = !whiteToMove;

            // this just here to prevent infinite loops for now
            break;
        }
    }

    /**
     * Moves back one move in the history
     */
    private void undo() {
        //TODO: implement
    }

    /**
     * Moves forward one move in the history
     */
    private void redo() {
        //TODO: implement
    }
}
