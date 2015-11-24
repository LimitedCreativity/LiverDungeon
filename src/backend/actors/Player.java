package backend.actors;

/**
 * Created by ryan on 11/23/15.
 */
public class Player extends Mob
{
    public final String NAME;
    public final PlayerColor COLOR;
    
    public Player(int x, int y, String name, PlayerColor color)
    {
        super(x, y);
        NAME = name;
        COLOR = color;
    }
    
    public static enum PlayerColor {
        
    }
}
