package backend.actors;

/**
 * Created by ryan on 11/23/15.
 */
public abstract class Actor
{
    protected int x, y;
    
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
