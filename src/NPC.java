public class NPC extends Player {

    public NPC() {
        super("Bot");
    }

    @Override
    public String getNAME() {
        return super.getNAME();
    }

    public void playNPC(Board board, int count) {
        String ownSort;
        if (count == 2) {
            ownSort = "X";
        } else {
            ownSort = "O";
        }

        String[][] boardCopy = copyBoard(board.getPieces());
        String move = bestMove(boardCopy, ownSort);
        System.out.printf("%s speelde %s\n", getNAME(), move);
        //TODO change the moves in the NPC with cords instead of a String
       // board.place(move, false);
    }

    private String[][] copyBoard(Piece[][] pieces) {
        int size = pieces.length;
        String[][] boardCopy = new String[size][size];

        for (int i = 0; i < boardCopy.length; i++) {
            for (int j = 0; j < boardCopy[i].length; j++) {
                if (pieces[i][j] == null) {
                    boardCopy[i][j] = null;
                } else if (pieces[i][j].equalsSort(Sort.X)) {
                    boardCopy[i][j] = "X";
                } else {
                    boardCopy[i][j] = "O";
                }
            }
        }
        return boardCopy;
    }

    private boolean movesAble(String[][] boardCopy) {
        for (String[] strings : boardCopy) {
            for (String string : strings) {
                if (string == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private int evaluation(String ownSort, String[][] boardCopy) {
return 0;
    }

    //determine the best value to play
    private int minimax(String[][] boardCopy, int depth, boolean max, String ownSort) {
        int score = evaluation(ownSort, boardCopy);
        String opponent = oppositeSort(ownSort);

        if (score == 10){
            return score;
        } else if (score == -10) {
            return score;
        }else if (movesAble(boardCopy)){
            return 0;
        } else if (max){
            int best = -1000;

            for (int i = 0; i < boardCopy.length; i++) {
                for (int j = 0; j < boardCopy[i].length; j++) {
                    if (boardCopy[i][j] == null){
                        boardCopy[i][j] = ownSort;

                        best = Math.max(best, minimax(boardCopy, depth + 1, false, ownSort));

                        boardCopy[i][j] = null;
                    }
                }
            }
            return best;
        } else{
            int best = 1000;

            for (int i = 0; i < boardCopy.length; i++) {
                for (int j = 0; j < boardCopy.length; j++) {
                    if (boardCopy[i][j] == null){
                        boardCopy[i][j] = opponent;

                        best = Math.min(best, minimax(boardCopy, depth + 1, true, ownSort));

                        boardCopy[i][j] = null;
                    }
                }
            }
            return best;
        }
    }

    private String bestMove(String[][] boardCopy, String ownSort){
        boolean max;
        max = !ownSort.equals("X");
        int bestVal = -1000;
        int column = -1;
        int row = -1;

        for (int i = 0; i < boardCopy.length; i++) {
            for (int j = 0; j < boardCopy[i].length; j++) {
                if (boardCopy[i][j] == null){
                    boardCopy[i][j] = ownSort;

                    int moveVal = minimax(boardCopy, 0, max, ownSort);

                    boardCopy[i][j] = null;

                    if (moveVal > bestVal){
                        row = i + 1;
                        column = j + 1;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return String.format("%d-%d", row, column);
    }

    private String oppositeSort(String sort){
        if (sort.equals("X")){
            return "O";
        } else{
            return "X";
        }
    }
}