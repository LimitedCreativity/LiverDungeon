package backend.actors;

import backend.Level;
import backend.Location;

import java.util.ArrayList;

/**
 * Created by ryan on 11/23/15.
 */
public class Item extends Actor
{
    public final Type type;

    public Item(Location loc, Type type)
    {
        super(loc);
        this.type = type;
    }
    
    public Item(int x, int y, Type type)
    {
        super(x, y);
        this.type = type;
    }
    
    public static enum Type {
        GOLD,
        TELEPORTER,
        EXIT
    }

    public void interact(Level level, Player player)
    {
        switch(type)
        {
            case TELEPORTER:
                teleportPlayer(level,player);
                break;
            default:
                return;
        }
    }

    private void teleportPlayer(Level level, Player player)
    {
        if(level.teleportersEnabled)
        {
            level.teleportersEnabled = false;

            ArrayList<Item> tps = new ArrayList<Item>();
            for (Actor a : level.getActors())
            {
                if (a instanceof Item)
                {
                    Item i = (Item) a;
                    if (i.type == Type.TELEPORTER)
                        tps.add(i);
                }
            }

            System.out.println(tps.size());
            tps.remove(this);
            System.out.println(tps.size());

            int rand = (int) Math.random() * tps.size();

            Item tp = tps.get(rand);
            Location tpLoc = tp.getLocation();

            player.setLocation(tpLoc);

        }


    }
}
