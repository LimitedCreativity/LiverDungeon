package backend.actors;

/**
 * Created by ryan on 11/23/15.
 */
public class Item extends Actor
{
    public final Type type;
    
    public Item(int x, int y, Type type)
    {
        super(x, y, 0);
        this.type = type;
    }

    
    public static enum Type {
        
    }
}
