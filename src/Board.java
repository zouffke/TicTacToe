import java.util.ArrayList;
import java.util.List;

public class Board {

    private static int width = 3;
    private static int length = 3;
    private Piece[][] pieces;

    public Board(int width, int length) {
        if (width == length && width == 3 || width == 6 || width == 9) {
            Board.width = width;
            Board.length = length;
        } else {
            Board.width = 3;
            Board.length = 3;
        }
        Piece.setiD(0);
        pieces = new Piece[Board.width][Board.length];
    }

    public boolean place(int x, int y, Piece piece) {
        if (x > Board.width || y > Board.length || x < 0 || y < 0) {
            System.out.println("Dit vak bestaat niet");
            return false;
        } else if (pieces[x][y] != null) {
            System.out.println("Dit vak is a bezet");
            return false;
        } else {
            pieces[x][y] = piece;
            return true;
        }
    }

    public boolean win() {
        int count = 0;

        //horizontal
        for (int i = 0; i < pieces.length; i++){
            for (int j = 0; j < pieces.length; j++) {
                //O
                if (pieces[i][j].equalsSort(Sort.O)){
                   count++;
                } else{
                    count = 0
                }
            }
        }
    }
}
