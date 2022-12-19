import java.util.Random;

public class NPC extends Player {

    public NPC() {
        super("Bot");
    }

    @Override
    public String getNAME() {
        return super.getNAME();
    }

    public void playNPC(Board board, int count) {
        Random random = new Random();
        Sort sort;

        if (count == 1){
            sort = Sort.X;
        } else{
            sort = Sort.O;
        }

        if (!smartNPC(board, random, sort)) {
            dumbNPC(board, random);
        }
    }

    private void dumbNPC(Board board, Random random) {
        boolean validMove;
        int length = Board.getLength();
        int width = Board.getWidth();
        StringBuilder stringBuilder;

        do {
            stringBuilder = new StringBuilder();

            stringBuilder.append(random.nextInt(length) + 1).append("-").append(random.nextInt(width) + 1);

            validMove = board.place(stringBuilder.toString(), false);
            System.out.printf("%s speelde: %s\n", getNAME(), stringBuilder);
        } while (!validMove);
    }

    private boolean smartNPC(Board board, Random random, Sort sort) {
        Piece[][] pieces = board.getPieces();
        int size = Board.getWidth();
        int sizeArray = size - 1;

        if (pieces[0][0] != null || pieces[0][sizeArray] != null || pieces[sizeArray][0] != null || pieces[sizeArray][sizeArray] != null) {
            if (size == 3 && pieces[1][1] == null) {
                board.place("2-2", false);
                return true;
            }
        } else {
            String index = "";
            switch (random.nextInt(4) + 1) {
                case 1 -> index = "1-1";
                case 2 -> index = String.format("1-%d", size);
                case 3 -> index = String.format("%d-1", size);
                case 4 -> index = String.format("%d-%d", size, size);
            }
            board.place(index, false);
            return true;
        }
        if (board.win(Sort.oppositSort(sort), true)){

        } else if (board.win(sort, true)){

        }
        return false;
    }
}
