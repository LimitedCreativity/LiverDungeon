package backend.interfaces;

import backend.Level;
import backend.Stats;
import backend.actors.Actor;
import backend.actors.Player;

import java.util.ArrayList;

/**
 * Created by ryan on 11/23/15.
 */
public interface Display
{
    public void initLevel(Stats stats, Level level);
    public void updateStats(Stats stats);
    public void updateActors(Player player, ArrayList<Actor> actors);
}
