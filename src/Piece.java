import java.util.Objects;

public abstract class Piece {

    private Sort sort;
    private int x;
    private int y;
    private static int iD = 0;
    private int id;

    public Piece(Sort sort, int x, int y) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return x == piece.x && y == piece.y && id == piece.id && sort == piece.sort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sort, x, y, id);
    }

    public boolean equalsSort(Sort sort){
        return this.getSort().equals(sort);
    }
}
