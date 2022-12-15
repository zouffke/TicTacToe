import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Contribution contribution;
        Scanner keyboard = new Scanner(System.in);
        Board board = new Board(3, 3);
        boolean validMove;
        int count = 1;
        boolean end;

        System.out.print("Geef de naam van speler 1: ");
        String name1 = keyboard.nextLine();
        System.out.print("Geef de naam van speler 2: ");
        String name2 = keyboard.nextLine();

        contribution = new Contribution(name1, name2);

        System.out.printf("%s speelt met %s\n", contribution.getName(1), contribution.getSort(1));
        System.out.printf("en\n%s speelt met %s\n", contribution.getName(2), contribution.getSort(2));

        board.drawBoard();

        do {
            if (count % 2 != 0) {
                System.out.printf("\n%s's beurt;\n", contribution.getSort(1).equals("X") ? contribution.getName(1) : contribution.getName(2));
            } else {
                System.out.printf("\n%s's beurt;\n", contribution.getSort(1).equals("O") ? contribution.getName(1) : contribution.getName(2));
            }

            do {
                System.out.print("Waar wilt u een stuk plaatsen?\nLocatie: ");
                validMove = board.place(keyboard.nextInt());
            } while (!validMove);

            board.drawBoard();
            end = board.draw();

            if (end) {
                System.out.println("It's a Draw!");
            } else if (count % 2 != 0) {
                end = board.win(Sort.X);
                if (end) {
                    System.out.printf("%s heeft gewonnen", contribution.getSort(1).equals("X") ? contribution.getName(1) : contribution.getName(2));
                }
                count++;
            } else {
                end = board.win(Sort.O);
                if (end) {
                    System.out.printf("%s heeft gewonen", contribution.getSort(1).equals("O") ? contribution.getName(1) : contribution.getName(2));
                }
                count = 1;
            }

        } while (!end);
    }
}
