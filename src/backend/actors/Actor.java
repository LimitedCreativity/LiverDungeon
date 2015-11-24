package backend.actors;

import backend.Location;

/**
 * Created by ryan on 11/23/15.
 */
public abstract class Actor
{
    protected int x, y;

    public Actor(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
    }

    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
}
