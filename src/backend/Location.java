package backend;

/**
 * Created by ryan on 11/24/15.
 */
public class Location
{
    private int x, y;

    public Location(int x, int y)
    {
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

    public int getTileX()
    {
        return x / Tile.SIZE;
    }

    public int getTileY()
    {
        return y / Tile.SIZE;
    }
}
