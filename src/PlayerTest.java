import java.util.Arrays;

public class PlayerTest {

    public static void main(String[] args) {
        Player p = new Player();
        for (int i = 0; i < 1; i++) {
            for(int[] a : p.getMove()) {
                System.out.print(Arrays.toString(a));
            }
            System.out.println();
        }
    }
}
