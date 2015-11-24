package backend;

import backend.actors.Actor;
import backend.actors.Player;
import backend.interfaces.*;

import java.util.ArrayList;
import java.util.logging.*;

/**
 * Created by ryan on 11/23/15.
 */
public class Game
{
    private static final long TIME_PER_UPDATE = 16L;
    private Display display;
    private Input input;

    private Level currentLevel;
    private ArrayList<Actor> actors;

    public Game(Display display, Input input)
    {
        this.display = display;
        this.input = input;
        initGame();
        start();
    }

    private void initGame()
    {
        currentLevel = new Level();
    }

    public void start()
    {
        display.initLevel(currentLevel);

        long previous = System.currentTimeMillis();
        long lag = 0;
        while(true)
        {
            long current = System.currentTimeMillis();
            long elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag > TIME_PER_UPDATE)
            {
                step();
                lag -= TIME_PER_UPDATE;

                display.updateActors(currentLevel.getPlayer(), currentLevel.getActors());
            }

        }

    }

    public void step()
    {
        CommandState commandState = input.getPlayerCommandState();

        if(commandState.NEW_LEVEL)
        {
            currentLevel = new Level();
            display.initLevel(currentLevel);
        }

        int horizontalDirection = 0, verticalDirection = 0;

        /* TODO: give actors a direction, then just update players direction */
        if(commandState.MOVE_DOWN)
        {
            verticalDirection = 1;
        }
        else if (commandState.MOVE_UP)
        {
            verticalDirection = -1;
        }
        if(commandState.MOVE_RIGHT)
        {
            horizontalDirection = 1;
        }
        else if(commandState.MOVE_LEFT)
        {
            horizontalDirection = -1;
        }

        currentLevel.getPlayer().move(horizontalDirection, verticalDirection, currentLevel);



    }
}
