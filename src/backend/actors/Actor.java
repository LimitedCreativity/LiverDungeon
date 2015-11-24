package backend.actors;

import backend.Location;
import backend.Tile;

/**
 * Created by ryan on 11/23/15.
 */
public abstract class Actor
{
    protected int x, y, size = Tile.SIZE;

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

    public boolean isCollidedWith(Actor actor)
    {
        int leftX = this.getX(), rightX = leftX + size;
        int topY = this.getY(), bottomY = topY + size;

        int actorLeftX = actor.getX(), actorRightX = actorLeftX + size;
        int actorTopY = actor.getY(), actorBottomY = actorTopY + size;

        if( ( leftX > actorLeftX && leftX < actorRightX ) ||
                (rightX < actorRightX && rightX > actorLeftX))
        {
            if(topY > actorTopY && topY < actorBottomY)
                return true;
            if(bottomY < actorBottomY && bottomY > actorTopY)
                return true;
        }

        return false;
    }
}
