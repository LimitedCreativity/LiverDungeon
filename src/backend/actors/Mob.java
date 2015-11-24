package backend.actors;

import backend.Level;
import backend.Location;
import backend.Tile;

/**
 * Created by ryan on 11/23/15.
 */
public abstract class Mob extends Actor
{
    protected int moveSpeed;

    public Mob(Location loc, int moveSpeed)
    {
        super(loc);
        this.moveSpeed = moveSpeed;
    }

    public Mob(int x, int y, int moveSpeed)
    {
        super(x, y);
        this.moveSpeed = moveSpeed;
    }

    public int getMoveSpeed(){return moveSpeed;}

    public void move(int horizontalDirection, int verticalDirection, Level level)
    {
        int possibleX = this.getX() + this.getMoveSpeed()*horizontalDirection;
        int possibleY = this.getY() + this.getMoveSpeed()*verticalDirection;
        if (possibleX != x && possibleX > -1 && possibleX < (level.getWidth()-1) * Tile.SIZE && level.notInWall(possibleX,y))
        {
            this.setX(possibleX);
        }
        if (possibleY != y && possibleY > -1 && possibleY < (level.getHeight()-1)*Tile.SIZE && level.notInWall(x,possibleY))
        {
            this.setY(possibleY);
        }
    }
}
