package backend;

/**
 * Created by ryan on 11/23/15.
 */
public class Tile
{
    public static final int SIZE = 25;
    
    public final Type type;
    
    public Tile(Type type)
    {
        this.type = type;
    }
    
    
    public static enum Type
    {
        FLOOR,
        WALL
    }
}
