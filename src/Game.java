import java.util.Stack;

public class Game {
    private Player[] players;
    private Stack<Board> undoStack;
    private Stack<Board> redoStack;
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

    }
}
