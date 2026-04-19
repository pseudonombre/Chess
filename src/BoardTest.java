import Move.Move;

public class BoardTest {
    public static void main(String[] args) {
        Board stupid = new Board();
        System.out.println(stupid.getString());
        Move move = new Move(new int[] {2, 2} , null, Move.CaptureStatus.ANY);
        stupid.makeMove(move, new int[]{1, 1});
        System.out.println(stupid.getString());
    }
}
