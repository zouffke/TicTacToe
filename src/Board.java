public class Board {

    private static int width = 3;
    private static int length = 3;
    private Piece[][] pieces;
    private int count = 1;

    public Board(int width, int length) {
        if (width == length && width == 3 || width == 6 || width == 9) {
            Board.width = width;
            Board.length = length;
        } else {
            Board.width = 3;
            Board.length = 3;
        }
        Piece.setiD(0);
        pieces = new Piece[Board.width][Board.length];
    }

    public boolean place(int index) {
        if (index < 1 || index > 9) {
            System.out.println("Dit vak bestaat niet");
            return false;
        }
        int x = 0;
        int y = 0;
        switch (index) {
            case 2:
                y = 1;
                break;
            case 3:
                y = 2;
                break;
            case 4:
                x = 1;
                break;
            case 5:
                x = 1;
                y = 1;
                break;
            case 6:
                x = 1;
                y = 2;
                break;
            case 7:
                x = 2;
            case 8:
                x = 2;
                y = 1;
                break;
            case 9:
                x = 2;
                y = 2;
        }
        if (pieces[x][y] != null) {
            System.out.println("Dit vak is al bezet");
            return false;
        } else {
            if (this.count % 2 != 0) {
                pieces[x][y] = new Piece(Sort.X, x, y);
                this.count++;
            } else {
                pieces[x][y] = new Piece(Sort.O, x, y);
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

        for (int i = 0; i < pieces.length; i++) {

            countH = 0;
            countV = 0;

            for (int j = 0; j < pieces.length; j++) {
                //horizontal
                if (pieces[i][j] == null) {
                    countH = 0;
                } else if (pieces[i][j].equalsSort(sort)) {
                    countH++;
                } else {
                    countH = 0;
                }

                //vertical
                if (pieces[j][i] == null) {
                    countV = 0;
                } else if (pieces[j][i].equalsSort(sort)) {
                    countV++;
                } else {
                    countV = 0;
                }
            }

            if (countH >= 3 || countV >= 3) {
                return true;
            }

            //diagonal left to right
            if (pieces[i][i] == null) {
                countDLtoR = 0;
            } else if (pieces[i][i].equalsSort(sort)) {
                countDLtoR++;
            } else {
                countDLtoR = 0;
            }

            //diagonal right to left
            if (pieces[pieces.length - 1 - i][i] == null) {
                countDRtoL = 0;
            } else if (pieces[pieces.length - 1 - i][i].equalsSort(sort)) {
                countDRtoL++;
            } else {
                countDRtoL = 0;
            }
        }
        return countDLtoR >= 3 || countDRtoL >= 3;
    }

    public boolean draw() {
        return Piece.getiD() == pieces.length;
    }

    public void drawBoard() {
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder("_______________\n|             |\n");
        for (int i = 0; i < pieces.length; i++) {
            stringBuilder.append("|  ");
            for (int j = 0; j < pieces.length; j++) {
                if (pieces[i][j] == null) {
                    stringBuilder.append(count++);
                } else {
                    stringBuilder.append(pieces[i][j]);
                    count++;
                }
                if (j == 2) {
                    stringBuilder.append("  |\n");
                } else {
                    stringBuilder.append(" | ");
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
