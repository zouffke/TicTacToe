import java.util.Objects;

public class Piece {

    private final Sort SORT;
    private final int X;
    private final int Y;
    private static int id = 0;

    public Piece(Sort sort, int x, int y) {
        this.X = x;
        this.Y = y;
        this.SORT = sort;
    }

    public Sort getSORT() {
        return SORT;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Piece.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return X == piece.X && Y == piece.Y && SORT == piece.SORT;
    }

    @Override
    public int hashCode() {
        return Objects.hash(SORT, X, Y);
    }

    public boolean equalsSort(Sort sort) {
        try {
            return this.getSORT().equals(sort);
        } catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%s", this.getSORT());
    }
}
