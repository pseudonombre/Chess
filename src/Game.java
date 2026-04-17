import Move.Move;

import java.util.Scanner;
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
        //TODO: display starting info
        Player currentPlayer = players[0];
        while(true) {
            //Print board
            System.out.println(undoStack.peek().getString());
            //get input
            int[][] currentInput = getInput();
            //currentInput is null if the player entered something invalid
            if(currentInput == null) {
                System.out.println("Invalid input");
                continue;
            }
            //non-normal move inputs return lengths of one
            if(currentInput.length == 1){
                switch(currentInput[0][0]){
                    case Player.otherInputs.OFFER_DRAW:
                        if(offerDraw()) {
                            endScreen(false, true);
                            return;
                        }
                        continue;
                    case Player.otherInputs.RESIGN:
                        endScreen(!whiteToMove);
                        return;
                    case Player.otherInputs.UNDO:
                        undo();
                        continue;
                    case Player.otherInputs.REDO:
                        redo();
                        continue;
                }
            }
            Move currentMove = undoStack.peek().getMove(currentInput);
            //test if it is legal
            if(currentMove == null) {

                System.out.println("Illegal move");
                continue;
            }
            //play it
            undoStack.push(new Board(undoStack.peek()));
            undoStack.peek().makeMove(currentMove, currentInput[0]);
            redoStack.clear();
            //TODO: Check for checkmate and stalemate / king being captured
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
     * offers a draw to the other player
     * @return true if draw accepted
     */
    private boolean offerDraw() {
        // playerOffered is the opposite of the player whose turn it is
        String playerOffered;
        if(whiteToMove){
            playerOffered = players[1].getName();
        } else {
            playerOffered = players[0].getName();
        }
        System.out.println(playerOffered + ", do you accept the draw offer? (Y or N): ");
        String input = new Scanner(System.in).nextLine();
        input = input.strip().toLowerCase();
        if(input == "y" || input == "yes") {
            return true;
        }
        return false;
    }

    /**
     * Moves back one move in the history
     */
    private void undo() {
        redoStack.push(undoStack.pop());
        whiteToMove = !whiteToMove;
    }

    /**
     * Moves forward one move in the history
     */
    private void redo() {
        undoStack.push(redoStack.pop());
        whiteToMove = !whiteToMove;
    }

    /**
     * Displays end screen
     */
    private void endScreen(boolean whiteWins) {
        endScreen(whiteWins, false);
    }

    /**
     * Displays end screen
     */
    private void endScreen(boolean whiteWins, boolean draw) {
        if(draw) {
            System.out.println("It's a draw!");
            return;
        }
        if(whiteWins) {
            System.out.print(players[0].getName());
        } else {
            System.out.print(players[1].getName());
        }
        System.out.println(" wins!");
    }
}
