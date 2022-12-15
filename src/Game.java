import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Contribution contribution;
        Scanner keyboard = new Scanner(System.in);
        Board board = new Board(3, 3);

        System.out.print("Geef de naam van speler 1: ");
        String name1 = keyboard.nextLine();
        System.out.print("Geef de naam van speler 2: ");
        String name2 = keyboard.nextLine();

        contribution = new Contribution(name1, name2);

        board.draw();
    }
}
