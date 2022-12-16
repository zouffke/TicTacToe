import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Contribution contribution;
        Scanner keyboard = new Scanner(System.in);
        Board board = new Board(9, 9);


        contribution = getPlayers(keyboard);

        play(contribution, board, keyboard);

    }

    public static Contribution getPlayers(Scanner keyboard){
        String name1;
        String name2;

        do {
            System.out.print("Geef de naam van speler 1: ");
            name1 = keyboard.nextLine();
        } while (name1.isEmpty() || !Character.isAlphabetic(name1.charAt(0)));
        do {
            System.out.print("Geef de naam van speler 2: ");
            name2 = keyboard.nextLine();
        } while (name2.isEmpty() || !Character.isAlphabetic(name2.charAt(0)));

        return new Contribution(name1, name2);
    }

    public static void play(Contribution contribution, Board board, Scanner keyboard){
        int count = 1;
        boolean validMove;


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
                System.out.print("Waar wilt u een stuk plaatsen?\nLocatie: ");
                validMove = board.place(keyboard.nextInt());
            } while (!validMove);

            board.drawBoard();

        } while (!winCheck(board, count, contribution));
    }

    public static boolean winCheck(Board board, int count, Contribution contribution){

        if (board.draw()) {
            System.out.println("It's a Draw!");
            return true;
        } else if (count % 2 == 0) {
            if (board.win(Sort.X)) {
                System.out.printf("%s heeft gewonnen", contribution.getSort(1).equals("X") ? contribution.getName(1) : contribution.getName(2));
                return true;
            }
        } else {
            if (board.win(Sort.O)) {
                System.out.printf("%s heeft gewonen", contribution.getSort(1).equals("O") ? contribution.getName(1) : contribution.getName(2));
                return true;
            }
        }
        return false;
    }
}
