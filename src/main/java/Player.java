import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private static final List<Player> playerList = new ArrayList<>();
    public Player(String name){
        this.name = name;
        playerList.add(this);
    }
    public static String getName(int playerNumber){
        if (playerNumber>0) {
            int player = playerNumber - 1;
            return playerList.get(player).name;
        }
        return null;
    }
}
