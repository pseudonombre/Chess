import Move.Move;

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
        Player currentPlayer = players[0];
        while(true) {
            //get input
            int[][] currentInput = getInput();
            Move currentMove = undoStack.peek().getMove(currentInput);
            //test if it is legal
            if(currentMove == null) { continue; }
            //play it
            undoStack.push(new Board(undoStack.peek()));
            undoStack.peek().makeMove(currentMove, currentInput[0]);
            //TODO: Check for checkmate and stalemate
            //make it the other player's move
            whiteToMove = !whiteToMove;

            // this just here to prevent infinite loops for now
            break;
        }
    }

    /**
     * Gets the input from a player
     * @return An array containing the beginning and end points of the move
     */
    private int[][] getInput() {
        Player currentPlayer;
        if(whiteToMove){
            currentPlayer = players[0];
        } else {
            currentPlayer = players[1];
        }
        return currentPlayer.getMove();
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
