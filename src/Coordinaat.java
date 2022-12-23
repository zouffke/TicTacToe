public class Coordinaat {
    private int x;
    private int y;

    public Coordinaat(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", x, y) ;
    }
}
