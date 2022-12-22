import java.util.Random;

public class NPC {
    Random random = new Random();
    int x = random.nextInt();
    int y = random.nextInt();
    private static String name;

    public NPC(String name) {
        NPC.name = name;
    }

    public static void setName(String name) {
        NPC.name = name;
    }

    public void play(Board board) {

    }
}