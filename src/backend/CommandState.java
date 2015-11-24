package backend;

/**
 * Created by ryan on 11/23/15.
 */
public class CommandState
{
    public boolean MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT;
    public boolean NEW_LEVEL;

    public CommandState()
    {
        MOVE_UP = false;
        MOVE_DOWN = false;
        MOVE_LEFT = false;
        MOVE_RIGHT = false;
        NEW_LEVEL = false;
    }
}
