package backend;

/**
 * Created by ryan on 11/23/15.
 */
public class Tile
{
    public static final int SIZE = 25;
    
    public final Type TYPE;
    
    public Tile(Type type)
    {
        TYPE = type;
    }
    
    
    public static enum Type
    {
        FLOOR,
        WALL
    }
}
