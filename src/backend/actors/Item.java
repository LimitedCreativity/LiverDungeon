package backend.actors;

import backend.Location;

/**
 * Created by ryan on 11/23/15.
 */
public class Item extends Actor
{
    public final Type type;

    public Item(Location loc, Type type)
    {
        super(loc);
        this.type = type;
    }
    
    public Item(int x, int y, Type type)
    {
        super(x, y);
        this.type = type;
    }
    
    public static enum Type {
        GOLD,
        EXIT
    }
}
