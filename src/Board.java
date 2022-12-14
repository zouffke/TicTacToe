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

    public boolean winO() {
        int countH;
        int countV;
        int countDLtoR = 0;
        int countDRtoL = 0;

        for (int i = 0; i < pieces.length; i++){

            countH = 0;
            countV = 0;

            for (int j = 0; j < pieces.length; j++) {
                //horizontal
                if (pieces[i][j].equalsSort(Sort.O)){
                   countH++;
                } else{
                    countH = 0;
                }

                //vertical
                if (pieces[j][i].equalsSort(Sort.O)){
                    countV++;
                } else{
                    countV = 0;
                }
            }

            if (countH >= 3 || countV >= 3){
                return true;
            }

            //diagonal left to right
            if (pieces[i][i].equalsSort(Sort.O)){
                countDLtoR++;
            } else {
                countDLtoR = 0;
            }

            //diagonal right to left
            if (pieces[pieces.length - 1 - i][i].equalsSort(Sort.O)){
                countDRtoL++;
            } else{
                countDRtoL = 0;
            }
        }
        return countDLtoR >= 3 || countDRtoL >= 3;
    }
}
