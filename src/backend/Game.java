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
    private static final long TIME_PER_UPDATE = 5L;
    private Display display;
    private Input input;

    private Level currentLevel;
    private Player player;
    private ArrayList<Actor> actors;

    public Game(Display display, Input input)
    {
        this.display = display;
        this.input = input;
    }

    private void initGame()
    {
        player = new Player(0,0,"");
        actors = new ArrayList<Actor>();
    }

    public void start()
    {
        long previous = System.currentTimeMillis();
        long lag = 0;
        while(true)
        {
            long current = System.currentTimeMillis();
            long elasped = current - previous;
            previous = current;
            lag += elasped;

            while (lag > TIME_PER_UPDATE)
            {
                step();
                lag -= TIME_PER_UPDATE;
            }

            display.updateActors(player, actors);
        }

    }

    public void step()
    {
        //  input
        CommandState commandState = input.getPlayerCommandState();

        int horizontalDirection, verticalDirection;

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
        else if(commandState.MOVE_RIGHT)
        {
            horizontalDirection = -1;
        }
        //  logic

        //  output

    }
}
