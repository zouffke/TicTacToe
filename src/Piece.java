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
        this.id = iD++;
    }

    public Sort getSort() {
        return sort;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getiD() {
        return iD;
    }

    public int getId() {
        return id;
    }
}
