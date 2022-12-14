import java.util.Random;

public enum Sort {
    X, O;

    public static Sort randomSort(){
        Random random = new Random();
        return Sort.values()[random.nextInt(2)];
    }
}
