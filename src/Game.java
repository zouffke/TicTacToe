import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Contribution contribution;
        Scanner keyboard = new Scanner(System.in);
        Board board;
        boolean loop;

        do {
            board = setBoard(keyboard);

            contribution = getPlayers(keyboard);

            play(contribution, board, keyboard);

            loop = again(keyboard);

        } while (loop);
    }

    public static Board setBoard(Scanner keyboard) {
        int choice;

        System.out.print("Kies het soort bord dat u wilt gebruiken:\n1: 3x3\n2: 6x6\n3: 7x7\n4: 9x9\n\tKeuze: ");
        choice = keyboard.nextInt();

        switch (choice) {
            case 1 -> {
                return new Board(3, 3);
            }
            case 2 -> {
                return new Board(6, 6);
            }
            case 3 -> {
                return new Board(7, 7);
            }
            case 4 -> {
                return new Board(9, 9);
            }
            default -> {
                System.out.println("Deze keuze bestaat niet.\nStandaard bord van 3x3 wordt gebruikt.");
                return new Board(3, 3);
            }
        }
    }

    public static Contribution getPlayers(Scanner keyboard) {
        String name1;
        String name2;

        do {
            keyboard.nextLine();
            System.out.print("Geef de naam van speler 1: ");
            name1 = keyboard.nextLine();
        } while (name1.isEmpty() || !Character.isAlphabetic(name1.charAt(0)));
        do {
            System.out.print("Geef de naam van speler 2: ");
            name2 = keyboard.nextLine();
        } while (name2.isEmpty() || !Character.isAlphabetic(name2.charAt(0)));

        return new Contribution(name1, name2);
    }


    public static void play(Contribution contribution, Board board, Scanner keyboard) {
        int count = 1;
        boolean validMove;
        String input;

        System.out.printf("%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));

        board.drawBoard();

        do {
            if (count++ % 2 != 0) {
                System.out.printf("\n%s's beurt;\n", contribution.getSort(1).equals("X") ? contribution.getName(1) : contribution.getName(2));
            } else {
                System.out.printf("\n%s's beurt;\n", contribution.getSort(1).equals("O") ? contribution.getName(1) : contribution.getName(2));
                count = 1;
            }
            do {
            validMove = board.place(splisten(keyboard));

            }while(!validMove);


            board.drawBoard();

        } while (!winCheck(board, count, contribution));
    }

    public static Coordinaat splisten(Scanner keyboard) {
        boolean validMove;
        String input;
        do {
            validMove = false;
            System.out.print("Waar wilt u een stuk plaatsen?\nLocatie: ");
            input = keyboard.nextLine();
            if (!input.isEmpty() && input.matches("[0-9]" + "-" + "[0-9]")) {
                validMove = true;
            } else {
                System.out.println("Verkeerde notatie");
            }

        } while (!validMove);
        int x = Integer.parseInt(input.substring(0, 1));
        int y = Integer.parseInt(input.substring(2, 3));
        return new Coordinaat(x, y);
    }
    public static boolean winCheck(Board board, int count, Contribution contribution) {

        if (board.draw()) {
            System.out.println("It's a Draw!\n");
            return true;
        } else if (count % 2 == 0) {
            if (board.win(Sort.X)) {
                System.out.printf("%s heeft gewonnen\n", contribution.getSort(1).equals("X") ? contribution.getName(1) : contribution.getName(2));
                return true;
            }
        } else {
            if (board.win(Sort.O)) {
                System.out.printf("%s heeft gewonen\n", contribution.getSort(1).equals("O") ? contribution.getName(1) : contribution.getName(2));
                return true;
            }
        }
        return false;
    }

    public static boolean again(Scanner keyboard) {
        System.out.println("\nWilt u nog eens spelen? Y/N");
        return keyboard.nextLine().toUpperCase().contains("Y");
    }

}

