package backend.interfaces;

import backend.Level;
import backend.actors.Actor;
import backend.actors.Player;

import java.util.ArrayList;

/**
 * Created by ryan on 11/23/15.
 */
public interface Display
{
    public void initLevel(Level level);
    public void updateActors(Player player, ArrayList<Actor> actors);
}
