import java.util.Random;

public class NPC extends Player {

    public NPC() {
        super("Bot");
    }

    @Override
    public String getNAME() {
        return super.getNAME();
    }

    //TODO fix weird glitch where the NPC doesn't place a block move or places it off the board
    //TODO look into the minimax algortihm
    //TODO plan to make 2 difficulties
    public void playNPC(Board board, int count) {
        Random random = new Random();
        Sort sort;

        if (count == 2) {
            sort = Sort.X;
        } else {
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
        } while (!validMove);
        System.out.printf("%s speelde: %s\n", getNAME(), stringBuilder);
    }

    private boolean smartNPC(Board board, Random random, Sort sort) {
        Piece[][] pieces = board.getPieces();

        if (blocking(pieces, board, sort)) {
            return true;
        }
        return corners(pieces, random, board);
    }

    private boolean corners(Piece[][] pieces, Random random, Board board) {
        int size = Board.getWidth();
        int sizeArray = size - 1;
        String index = "";

        //pieces in the corners?
        if (pieces[0][0] != null || pieces[0][sizeArray] != null || pieces[sizeArray][0] != null || pieces[sizeArray][sizeArray] != null) {
            //Yes;
            //Block the piece in the corner by placing next to it
            if (pieces[0][0] != null && pieces[1][1] == null) {
                index = String.format("%d-%d", 2, 2);
            } else if (pieces[0][sizeArray] != null && pieces[1][sizeArray - 1] == null) {
                index = String.format("%d-%d", 2, size - 1);
            } else if (pieces[sizeArray][0] != null && pieces[sizeArray - 1][1] == null) {
                index = String.format("%d-%d", size - 1, 2);
            } else if (pieces[sizeArray][sizeArray] != null && pieces[sizeArray - 1][sizeArray - 1] == null) {
                index = String.format("%d-%d", size - 1, size - 1);
            }
            //No;
        } else {
            //Pick a random corner to place a piece
            switch (random.nextInt(4) + 1) {
                case 1 -> index = "1-1";
                case 2 -> index = String.format("1-%d", size);
                case 3 -> index = String.format("%d-1", size);
                case 4 -> index = String.format("%d-%d", size, size);
            }
        }

        if (!index.isEmpty()) {
            System.out.printf("%s speelde: %s\n", getNAME(), index);
            board.place(index, false);
            return true;
        }
        return false;
    }

    private boolean blocking(Piece[][] pieces, Board board, Sort sort) {
        Sort opponent = Sort.oppositSort(sort);
        int trigger;
        String index;

        if (Board.getLength() == 3) {
            trigger = 2;
        } else {
            trigger = 3;
        }

        if (!(index = blockingIndex(pieces, opponent, trigger)).isEmpty()) {
            System.out.printf("%s speelde: %s\n", getNAME(), index);
            board.place(index, false);
            return true;
        } else {
            return false;
        }
    }

    private String blockingIndex(Piece[][] pieces, Sort opponent, int trigger) {
        boolean loop = true;

        int counterV;
        int counterH;
        int counterDLtoR = 0;
        int counterDRtoL = 0;

        int indexNullH = 0;
        int indexNullV = 0;
        int indexNullDLtoR = 0;
        int indexNullDRtoL = 0;

        String playIndex = "";

        for (int i = 0; i < pieces.length && loop; i++) {
            counterV = 0;
            counterH = 0;
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == null) {
                    indexNullH = j + 1;
                } else if (pieces[i][j].equalsSort(opponent)) {
                    counterH++;
                } else {
                    counterH = 0;
                }

                if (pieces[j][i] == null) {
                    indexNullV = j + 1;
                } else if (pieces[j][i].equalsSort(opponent)) {
                    counterV++;
                } else {
                    counterV = 0;
                }
            }

            if (pieces[i][i] == null) {
                indexNullDLtoR = i + 1;
            } else if (pieces[i][i].equalsSort(opponent)) {
                counterDLtoR++;
            } else {
                counterDLtoR = 0;
            }

            if (pieces[pieces.length - 1 - i][i] == null) {
                indexNullDRtoL = i + 1;
            } else if (pieces[pieces.length - i - 1][i].equalsSort(opponent)) {
                counterDRtoL++;
            } else {
                counterDRtoL = 0;
            }

            if (counterH == trigger) {
                playIndex = String.format("%d-%d", i + 1, indexNullH);
                loop = false;
            }
            if (counterV == trigger) {
                playIndex = String.format("%d-%d", indexNullV, i + 1);
                loop = false;
            }
        }

        if (counterDLtoR == trigger){
            playIndex = String.format("%d-%d", indexNullDLtoR, indexNullDLtoR);
        }
        if (counterDRtoL == trigger){
            playIndex = String.format("%d-%d", indexNullDRtoL, indexNullDRtoL);
        }

        return playIndex;
    }
}
