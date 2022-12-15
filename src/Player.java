import java.util.List;

public class Player {
    private String name;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
