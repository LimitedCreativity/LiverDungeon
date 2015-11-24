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
    protected double rotation;

    public enum Direction {
        S,
        SW,
        W,
        NW,
        N,
        NE,
        E,
        SE
    }

    public Mob(Location loc, int moveSpeed)
    {
        super(loc);
        this.moveSpeed = moveSpeed;
        this.rotation = 0;
    }

    public Mob(int x, int y, int moveSpeed)
    {
        super(x, y);
        this.moveSpeed = moveSpeed;
        this.rotation = 0;
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

        //  Set rotation from move direction
        setRotation(horizontalDirection, verticalDirection);
    }

    private void setRotation(int horizontal, int vertical)
    {
        Direction dir;

        //  Moving west
        if(horizontal == -1)
        {
            //  Moving north
            if(vertical == -1)
                dir = Direction.NW;
            //  Moving south
            else if(vertical == 1)
                dir = Direction.SW;
            else
                dir = Direction.W;
        }
        //  Moving east
        else if(horizontal == 1)
        {
            //  Moving north
            if(vertical == -1)
                dir = Direction.NE;
            //  Moving south
            else if(vertical == 1)
                dir = Direction.SE;
            else
                dir = Direction.E;
        }
        //  Neither
        else
        {

            //  Moving north
            if(vertical == -1)
                dir = Direction.N;
            //  Moving south
            else
                dir = Direction.S;
        }

        rotation = dir.ordinal()*Math.PI/4;
    }

    public double getRotation()
    {
        return rotation;
    }
}
