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

    public Location getLocation()
    {
        return new Location(x,y);
    }

    public void setLocation(Location loc)
    {
        setX(loc.getX());
        setY(loc.getY());
    }

    public boolean isCollidedWith(Actor actor)
    {
        int TOLERANCE = 10;

        int leftX = this.getX() + TOLERANCE, rightX = this.getX() + size - TOLERANCE - 1;
        int topY = this.getY() + TOLERANCE, bottomY = this.getY() + size - TOLERANCE - 1;

        int actorLeftX = actor.getX(), actorRightX = actor.getX() + size - TOLERANCE - 1;
        int actorTopY = actor.getY(), actorBottomY = actor.getY() + size - TOLERANCE - 1;

        if( ( leftX >= actorLeftX && leftX <= actorRightX ) ||
                (rightX <= actorRightX && rightX >= actorLeftX))
        {
            if(topY >= actorTopY && topY <= actorBottomY)
                return true;
            if(bottomY <= actorBottomY && bottomY >= actorTopY)
                return true;
        }

        return false;
    }
}
