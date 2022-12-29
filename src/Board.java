public class Board {

    private static int width = 3;
    private static int length = 3;
    private final Piece[][] pieces;
    private int count = 1;


    public Board(int width, int length) {
        if (width == length && width == 3 || width == 6 || width == 7 || width == 9) {
            Board.width = width;
            Board.length = length;
        } else {
            Board.width = 3;
            Board.length = 3;
        }
        Piece.setId(0);
        this.pieces = new Piece[Board.width][Board.length];
    }

    public boolean place(Coordinaat index, boolean human) {
        int x = index.getX();
        int y = index.getY();

        if (x < 1 || x > getWidth() || y < 1 || y > getLength()) {
            if (human) {
                System.out.println("Dit veld bestaat niet");
            }
            return false;
        } else {
            x--;
            y--;
        }
        if (this.pieces[x][y] != null) {
            if (human) {
                System.out.println("Dit vak is al bezet");
            }
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
        //define the trigger
        int trigger;
        if (getWidth() == 3) {
            trigger = 3;
        } else {
            trigger = 4;
        }

        for (int y = 0; y < this.pieces.length; y++) {
            for (int x = 0; x < this.pieces[y].length; x++) {
                try {
                    if (this.pieces[y][x].equalsSort(sort)) {
                        for (int yy = -1; yy <= 1; yy++) {
                            for (int xx = -1; xx <= 1; xx++) {
                                //out of bounds check
                                if (!(yy + y < 0 || xx + x < 0 || yy + y >= this.pieces.length || xx + x >= this.pieces.length || (xx == 0 && yy == 0))) {
                                    if (repeat(sort, y, x, yy, xx, trigger, 0)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                } catch (NullPointerException ignored) {
                }
            }
        }
        return false;
    }

    private boolean repeat(Sort sort, int y, int x, int yy, int xx, int trigger, int index) {
        if (index == trigger - 1) {
            return true;
        } else {
            int counterY = 0;
            int counterX = 0;
            if (yy != 0) {
                if (yy > 0) {
                    counterY = index;
                } else {
                    counterY = Math.negateExact(index);
                }
            }
            if (xx != 0) {
                if (xx > 0) {
                    counterX = index;
                } else {
                    counterX = Math.negateExact(index);
                }
            }
            if (outOfBounds(y + yy + counterY, x + xx + counterX)) {
                return false;
            } else {
                try {
                    if (pieces[y + yy + counterY][x + xx + counterX].equalsSort(sort)) {
                        return repeat(sort, y, x, yy, xx, trigger, ++index);
                    } else {
                        return false;
                    }
                } catch (NullPointerException e) {
                    return false;
                }
            }
        }
    }

    private boolean outOfBounds(int y, int x) {
        return pieces.length <= y || pieces.length <= x || y < 0 || x < 0;
    }

    public boolean draw() {
        for (Piece[] piece : pieces) {
            for (Piece value : piece) {
                if (value == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getWidth() {
        return Board.width;
    }

    public static int getLength() {
        return Board.length;
    }

    public Piece[][] getPieces() {
        return this.pieces;
    }

    public void drawBoard() {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (getLength() == 3) {
            stringBuilder.append("_".repeat(15)).append("\n").append("|").append(" ".repeat(13)).append("|\n");
        } else if (getLength() == 6) {
            stringBuilder.append("_".repeat(27)).append("\n").append("|").append(" ".repeat(25)).append("|\n");
        } else if (getLength() == 7) {
            stringBuilder.append("_".repeat(31)).append("\n").append("|").append(" ".repeat(29)).append("|\n");
        } else {
            stringBuilder.append("_".repeat(39)).append("\n").append("|").append(" ".repeat(37)).append("|\n");
        }
        for (int i = 0; i < this.pieces.length; i++) {
            if (this.pieces[i][count++] == null) {
                stringBuilder.append("| ");
            } else {
                stringBuilder.append("| ");
            }
            for (int j = 0; j < this.pieces.length; j++) {
                if (this.pieces[i][j] == null) {
                    stringBuilder.append(i + 1).append("-").append(j + 1);
                } else {
                    stringBuilder.append(" ").append(this.pieces[i][j]);
                }
                if (j == getWidth() - 1) {
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
            if (i == getLength() - 1) {
                if (getLength() == 3) {
                    stringBuilder.append("|").append("_".repeat(13)).append("|\n");
                } else if (getLength() == 6) {
                    stringBuilder.append("|").append("_".repeat(25)).append("|\n");
                } else if (getLength() == 7) {
                    stringBuilder.append("|").append("_".repeat(29)).append("|\n");
                } else {
                    stringBuilder.append("|").append("_".repeat(37)).append("|\n");
                }
            } else {
                if (getLength() == 3) {
                    stringBuilder.append("| ").append("~".repeat(11)).append(" |\n");
                } else if (getLength() == 6) {
                    stringBuilder.append("| ").append("~".repeat(23)).append(" |\n");
                } else if (getLength() == 7) {
                    stringBuilder.append("| ").append("~".repeat(27)).append(" |\n");
                } else {
                    stringBuilder.append("| ").append("~".repeat(35)).append(" |\n");
                }
            }
        }
        System.out.print(stringBuilder);
    }
}
