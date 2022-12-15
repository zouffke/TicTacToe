public class Contribution {

    private Player[] players;
    private Sort[] sorts;

    public Contribution(String name1, String name2){
        players = new Player[]{new Player(name1), new Player(name2)};
        sorts = new Sort[2];
       //random sort toekennen.
        sorts[0] = Sort.randomSort();
        sorts[1] = Sort.randomSort(sorts[0]);
    }

}
