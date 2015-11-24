package backend;

import backend.actors.Actor;
import backend.actors.Player;

import java.util.ArrayList;

/**
 * Created by ryan on 11/23/15.
 */
public class World
{
    private static final int NUM_LEVELS = 10;
    private int currentLevel;
    private ArrayList<Level> levels;

    public World()
    {
        levels = new ArrayList<Level>();

        for(int i = 0; i < NUM_LEVELS; i++)
            levels.add(new Level());

        currentLevel = 0;
    }

    public void nextLevel()
    {
        currentLevel++;
    }

    public Level getCurrentLevel()
    {
        return levels.get(currentLevel);
    }

    public void resetCurrentLevel()
    {
        levels.set(currentLevel, new Level());
    }

    public int getLevelNumber()
    {
        return (currentLevel+1);
    }

    //  Should I have these here? I think they are good
    public Player getPlayer()
    {
        return getCurrentLevel().getPlayer();
    }

    public ArrayList<Actor> getActors()
    {
        return getCurrentLevel().getActors();
    }

}
