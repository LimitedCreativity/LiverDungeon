package backend.actors;

/**
 * Created by ryan on 11/23/15.
 */
public abstract class Mob extends Actor
{
    protected int moveSpeed;

    public Mob(int x, int y, int moveSpeed)
    {
        super(x, y);
        this.moveSpeed = moveSpeed;
    }

    public int getMoveSpeed(){return moveSpeed;}
}
