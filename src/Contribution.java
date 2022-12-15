public class Contribution {

    private final Player[] PLAYERS;
    private final Sort[] SORTS;

    public Contribution(String name1, String name2){
        PLAYERS = new Player[]{new Player(name1), new Player(name2)};
        SORTS = new Sort[2];
       //random sort toekennen.
        SORTS[0] = Sort.randomSort();
        SORTS[1] = Sort.randomSort(SORTS[0]);
    }

    public String getSort(int index){
        return String.format("%s", SORTS[index - 1]);
    }

    public String getName(int index){
        return PLAYERS[index - 1].getNAME();
    }
}
