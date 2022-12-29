import java.util.Objects;

public class Piece {

    private final Sort SORT;
    private final int X;
    private final int Y;

    public Piece(Sort sort, int y, int x) {
        this.X = x;
        this.Y = y;
        this.SORT = sort;
    }

    public Sort getSORT() {
        return SORT;
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
