package backend.actors;

/**
 * Created by ryan on 11/23/15.
 */
public abstract class Actor
{
    protected int x, y;
    protected int moveSpeed;
    
    public Actor(int x, int y, int moveSpeed) {
        this.x = x;
        this.y = y;
        this.moveSpeed = moveSpeed;
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

    public int getMoveSpeed(){return moveSpeed;}
}
