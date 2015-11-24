package backend.actors;

/**
 * Created by ryan on 11/23/15.
 */
public class Player extends Mob
{
    public final String name;

    private final static int INITIAL_MOVE_SPEED = 1;

    public Player(int x, int y, String name)
    {
        super(x, y, INITIAL_MOVE_SPEED);
        this.name = name;
    }
}
