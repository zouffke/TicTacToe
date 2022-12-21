import java.util.Random;

public class NPC {
    Random random = new Random();
    private static String name;
    public NPC (String name){
        NPC.name = name;
    }

    public static void setName(String name) {
        NPC.name = name;
    }
    public void play(Board board){
        board.place(random.Stringbuilder);
    StringBuilder stringBuilder = new StringBuilder();
    }
}