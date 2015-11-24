package backend.actors;

import backend.Level;
import backend.Location;

/**
 * Created by ryan on 11/23/15.
 */
public class Enemy extends Mob
{
    private static final int ENEMY_MOVE_SPEED = 1;
    public Rank rank;

    public enum Rank {
        GRUNT,
        WARRIOR,
        MASTER
    }

    public Enemy(Location loc)
    {
        super(loc, ENEMY_MOVE_SPEED);
        this.rank = Rank.GRUNT;
    }

    public Enemy(int x, int y)
    {
        super(x, y, ENEMY_MOVE_SPEED);
        this.rank = Rank.GRUNT;
    }

    public void move(Level level, Player player)
    {
        switch(rank)
        {
            case GRUNT:
                simpleMove(level,player);
                break;
            case WARRIOR:
            case MASTER:
                //  Use better algorithm, like A*
                break;
        }
    }

    //  Will need to get the CLOSEST PLAYER for multiplayer games.
    private void simpleMove(Level level, Player player)
    {
        int px = player.getX(), py = player.getY();

        int horizontal = 0, vertical = 0;

        if(px > x)
            horizontal = 1;
        else if(px < x)
            horizontal = -1;

        if(py > y)
            vertical = 1;
        else if(py < y)
            vertical = -1;

        super.move(horizontal,vertical,level);
    }

}
