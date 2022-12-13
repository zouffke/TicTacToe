import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int LENGTH = 3;
    private static final int WIDTH = 3;
    private List<List<Piece>> pieces;

    public Board(){
        pieces = new ArrayList<>();
    }

    public boolean place(int x, int y, Piece piece){
        if (x > WIDTH){
            System.out.println("Dit vak bestaat niet");
            return false;
        } else if (y > LENGTH) {
            System.out.println("Dit vak bestaat niet");
            return false;
        } else if (pieces.get(x - 1).get(y - 1) != null){
            System.out.println("Dit vak is a bezet");
            return false;
        } else{
            pieces.get(x - 1).add(y - 1, piece);
            return true;
        }
    }

    public boolean win(){
        int count = 0;
    }
}
