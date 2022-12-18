import java.util.Random;

public class NPC extends Player {

    public NPC() {
        super("Bot");
    }

    @Override
    public String getNAME() {
        return super.getNAME();
    }

    public void playNPC(Board board) {
        StringBuilder stringBuilder;
        int length = Board.getLength();
        int width = Board.getWidth();
        Random random = new Random();
        boolean validMove;

        //TODO NPC seems to not reach all the places
        do {
            stringBuilder= new StringBuilder();
            stringBuilder.append(random.nextInt(length) + 1).append("-").append(random.nextInt(width-1) + 1);
            validMove = board.place(stringBuilder.toString(), false);
        } while(!validMove);
    }
}
