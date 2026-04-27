package Move;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility to hold information about a move. Relative to [0,0].
 */
public class Move {
    /**
     * Destination of the move. Relative to [0,0].
     */
    private int[] destination;
    /**
     * @return the destination of the move. Relative to [0,0].
     */
    public int[] getDestination() { return destination.clone(); }

    /**
     * Spaces which must be empty for the move to be legal. Relative to [0,0].
     */
    private ArrayList<int[]> path;
    /**
     * @return The spaces which must be empty for the move to be legal. Relative to [0,0].
     */
    public ArrayList<int[]> getPath() {
        ArrayList<int[]> ret = new ArrayList<>();
        for(int[] a : path) {
            ret.add(a.clone());
        }
        return ret;
    }

    /**
     * Tostring method for debugging
     * @return a string representing the destination, path, and captureStatus of the move.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Start Move string\n");
        sb.append("destination: " + Arrays.toString(destination));
        sb.append("\npath:\n");
        for(int[] a: path) {
          sb.append(Arrays.toString(a) + "\n");
        }
        sb.append("captureStatus: " + captureStatus);
        sb.append("\nEnd Move string");
        return sb.toString();
    }

    /**
     * Possible capture statuses
     */
    public enum  CaptureStatus {
        ANY,
        MUST_CAPTURE,
        CANNOT_CAPTURE
    };
    /**
     * Describes whether a move is capable of capturing and whether it is capable of moving without capturing
     */
    private CaptureStatus captureStatus;
    /**
     * @return A description of whether a move is capable of capturing and whether it is capable of moving without capturing
     */
    public CaptureStatus getCaptureStatus() { return captureStatus; }


    /**
     * Possible special moves
     */
    public enum  SpecialMove {
        NORMAL,
        CASTLE,
        PAWN_TWO,
        EN_PASSANT
    };
    /**
     * Describes whether the move is a special move subject to extra rules
     */
    private SpecialMove specialMove;
    /**
     * @return A description of whether the move is a special move subject to extra rules
     */
    public SpecialMove getSpecialMove() { return specialMove; }

    /**
     * Constructs a move
     * @param c_destination Destination of the move. Relative to [0,0].
     * @param c_path Spaces which must be empty for the move to be legal. Relative to [0,0].
     * @param c_captureStatus Describes whether a move is capable of capturing and whether it is capable of moving without capturing
     * @param c_specialMove Describes whether the move is a special move subject to extra rules
     */
    public Move(int[] c_destination,
                ArrayList<int[]> c_path,
                CaptureStatus c_captureStatus,
                SpecialMove c_specialMove) {
        destination = c_destination.clone();
        path = new ArrayList<>();
        if(!(c_path == null)){
            path = new ArrayList<>();
            for (int[] space : c_path) {
                path.add(space.clone());
            }
        }
        captureStatus = c_captureStatus;
        specialMove = c_specialMove;
    }
}
