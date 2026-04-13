import java.util.ArrayList;

public class Rook extends Piece {
    private static final ArrayList<Move> possMoves = new Rook(true).getPossMoves();

    public Rook(boolean c_isWhite) {
        super(c_isWhite, 'R');
        //possMoves = getPossMoves();
    }

    @Override
    public Move getMove(int[] destination) {
        return null;
    }

    @Override
    public ArrayList<Move> getPossMoves() {
        if(possMoves != null){ return possMoves; }
        return null;
    }

}
