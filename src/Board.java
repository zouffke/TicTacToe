public class Board {

    private static int width = 3;
    private static int length = 3;
    private final Piece[][] pieces;
    private int count = 1;

    public Board(int width, int length) {
        if (width == length && width == 3 || width == 6 || width == 9) {
            Board.width = width;
            Board.length = length;
        } else {
            Board.width = 3;
            Board.length = 3;
        }
        Piece.setId(0);
        this.pieces = new Piece[Board.width][Board.length];
    }

    //TODO change the function so it works with a bigger board, cords will be given instead of a whole number
    public boolean place(int index) {
        if (index < 1 || index > 9) {
            System.out.println("Dit vak bestaat niet");
            return false;
        }
        int x = 0;
        int y = 0;
        switch (index) {
            case 2 -> y = 1;
            case 3 -> y = 2;
            case 4 -> x = 1;
            case 5 -> {
                x = 1;
                y = 1;
            }
            case 6 -> {
                x = 1;
                y = 2;
            }
            case 7 -> x = 2;
            case 8 -> {
                x = 2;
                y = 1;
            }
            case 9 -> {
                x = 2;
                y = 2;
            }
        }
        if (this.pieces[x][y] != null) {
            System.out.println("Dit vak is al bezet");
            return false;
        } else {
            if (this.count % 2 != 0) {
                this.pieces[x][y] = new Piece(Sort.X, x, y);
                this.count++;
            } else {
                this.pieces[x][y] = new Piece(Sort.O, x, y);
                this.count = 1;
            }
            return true;
        }
    }

    public boolean win(Sort sort) {
        int countH;
        int countV;
        int countDLtoR = 0;
        int countDRtoL = 0;

        for (int i = 0; i < this.pieces.length; i++) {

            countH = 0;
            countV = 0;

            for (int j = 0; j < this.pieces.length; j++) {
                //horizontal
                if (countH == 3) {
                    return true;
                } else if (this.pieces[i][j] == null) {
                    countH = 0;
                } else if (this.pieces[i][j].equalsSort(sort)) {
                    countH++;
                } else {
                    countH = 0;
                }

                //vertical
                if (countV == 3) {
                    return true;
                } else if (this.pieces[j][i] == null) {
                    countV = 0;
                } else if (this.pieces[j][i].equalsSort(sort)) {
                    countV++;
                } else {
                    countV = 0;
                }
            }

            if (countH >= 3 || countV >= 3) {
                return true;
            }

            //diagonal left to right
            if (countDLtoR == 3) {
                return true;
            } else if (this.pieces[i][i] == null) {
                countDLtoR = 0;
            } else if (this.pieces[i][i].equalsSort(sort)) {
                countDLtoR++;
            } else {
                countDLtoR = 0;
            }

            //diagonal right to left
            if (countDRtoL == 3) {
                return true;
            } else if (this.pieces[this.pieces.length - 1 - i][i] == null) {
                countDRtoL = 0;
            } else if (this.pieces[this.pieces.length - 1 - i][i].equalsSort(sort)) {
                countDRtoL++;
            } else {
                countDRtoL = 0;
            }
        }
        return countDLtoR >= 3 || countDRtoL >= 3;
    }

    public boolean draw() {
        return Piece.getId() == Board.getLength() * Board.getWidth();
    }

    public static int getWidth() {
        return Board.width;
    }

    public static int getLength() {
        return Board.length;
    }

    public void drawBoard() {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder("_______________\n|             |\n");
        for (int i = 0; i < this.pieces.length; i++) {
            if (this.pieces[i][count++] == null) {
                stringBuilder.append("| ");
            } else {
                stringBuilder.append("| ");
            }
            for (int j = 0; j < this.pieces.length; j++) {
                if (this.pieces[i][j] == null) {
                    stringBuilder.append(i + 1).append(" ").append(j + 1);
                } else {
                    stringBuilder.append(" ").append(this.pieces[i][j]);
                }
                if (j == 2) {
                    if (pieces[i][j] == null) {
                        stringBuilder.append(" |\n");
                    } else {
                        stringBuilder.append("  |\n");
                    }
                } else {
                    if (pieces[i][j] == null) {
                        stringBuilder.append("|");
                    } else {
                        stringBuilder.append(" |");
                    }
                }
            }
            if (i == 2) {
                stringBuilder.append("|_____________|\n");
            } else {
                stringBuilder.append("| ~~~~~~~~~~~ |\n");
            }
        }
        System.out.print(stringBuilder);
    }
}
