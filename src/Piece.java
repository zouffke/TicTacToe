public abstract class Piece {

    private Sort sort;
    private int x;
    private int y;
    private static int iD = 0;
    private int id;

    public Piece(Sort sort, int x, int y){
        this.x = x;
        this.y = y;
        this.sort = sort;
    }
}
