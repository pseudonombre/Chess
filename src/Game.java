import Move.Move;

import java.sql.SQLOutput;
import java.util.Arrays;
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
    private Stack<Board> undoStack = new Stack<>();
    /**
     * Future boards present if undo was used and no moves have been made since
     */
    private Stack<Board> redoStack = new Stack<>();
    /**
     * True if white moves next, false otherwise
     */
    private boolean whiteToMove = true;

    /**
     * Creates a new game
     */
    public Game() {
        players = new Player[2];
        undoStack.push(new Board());
    }

    /**
     * Plays a game. Gets player's moves and plays them in a loop until the game ends
     */
    public void play() {
        System.out.println("Welcome to Chess!");
        System.out.print("Please enter a name for Player 1 (white): ");
        players[0] = new Player(new Scanner(System.in).nextLine());
        System.out.print("Please enter a name for Player 2 (black): ");
        players[1] = new Player(new Scanner(System.in).nextLine());
        System.out.println("Each turn, the player will be asked to input a move in the format of two coordinates\n" +
                "E.G. \"a2 a4\" or \"a2 to a4\" to move white's a pawn two spaces\n" +
                "Also note that instead of taking a move, you may type \"offer draw\", \"resign\", \"undo\", or \"redo\"\n" +
                "to trigger the respective action.");
        System.out.println("Enjoy the game!");

        Player currentPlayer = players[0];
        while(true) {
            //Print board
            System.out.println(undoStack.peek().getString(whiteToMove));
            //get input
            int[][] currentInput = getInput();
            //currentInput is null if the player entered something invalid
            if(currentInput == null) {
                System.out.println("Invalid input");
                continue;
            }
            //non-normal move inputs return lengths of one
            if(currentInput.length == 1){
                //int offer_draw = Player.otherInputs.OFFER_DRAW.ordinal();
                switch(currentInput[0][0]){
                    // This really shouldn't be hardcoded but architecture is hard
                    case 0://Player.otherInputs.OFFER_DRAW:
                        if(offerDraw()) {
                            endScreen(false, true);
                            return;
                        }
                        continue;
                    case 1://Player.otherInputs.RESIGN:
                        endScreen(!whiteToMove);
                        return;
                    case 2://Player.otherInputs.UNDO:
                        undo();
                        continue;
                    case 3://Player.otherInputs.REDO:
                        redo();
                        continue;
                    default:
                        throw new IllegalArgumentException("Player.getMove() returned [" + currentInput[0] + "]");
                }
            }
            //System.out.println(Arrays.toString(currentInput[0]) + Arrays.toString(currentInput[1]));
            Move currentMove;
            try {
                currentMove = undoStack.peek().getMove(currentInput, whiteToMove);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                continue;
            }
//            //test if it is legal
//            if(currentMove == null) {
//                System.out.println("Illegal move");
//                continue;
//            }
            //play it
            undoStack.push(new Board(undoStack.peek()));
            undoStack.peek().makeMove(currentMove, currentInput[0]);
            redoStack.clear();
            //TODO: Check for checkmate and stalemate / king being captured
            //make it the other player's move
            whiteToMove = !whiteToMove;
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

    /**
     * Inputs other than normal moves
     */
    public enum otherInputs {
        OFFER_DRAW,
        RESIGN,
        UNDO,
        REDO
    }

    /**
     * lets outside classes access ordinals for this enum
     * @return the ordinal of the enumerated constant
     */
    public int getOrdinal(otherInputs e) {
        return e.ordinal();
    }
}
