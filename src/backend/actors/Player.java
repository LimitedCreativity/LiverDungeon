package backend.actors;

/**
 * Created by ryan on 11/23/15.
 */
public class Player extends Mob
{
    public final String NAME;
    
    public Player(int x, int y, String name)
    {
        super(x, y);
        NAME = name;
    }
}
