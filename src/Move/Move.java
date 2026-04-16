package Move;

import java.util.ArrayList;

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
    public int[] getDestination() { return destination; }

    /**
     * Spaces which must be empty for the move to be legal. Relative to [0,0].
     */
    private ArrayList<int[]> path;
    /**
     * @return The spaces which must be empty for the move to be legal. Relative to [0,0].
     */
    public ArrayList<int[]> getPath() { return path; }

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
     * Constructs a move
     * @param c_destination Destination of the move. Relative to [0,0].
     * @param c_path Spaces which must be empty for the move to be legal. Relative to [0,0].
     * @param c_captureStatus Describes whether a move is capable of capturing and whether it is capable of moving without capturing
     */
    public Move(int[] c_destination, ArrayList<int[]> c_path, CaptureStatus c_captureStatus) {
        destination = c_destination;
        if(path == null){
            path = new ArrayList<>();
        } else {
            path = c_path;
        }
        captureStatus = c_captureStatus;
    }
}
