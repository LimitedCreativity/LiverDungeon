package backend;

import backend.actors.Actor;
import backend.actors.Enemy;
import backend.actors.Item;
import backend.actors.Player;
import backend.interfaces.*;

import java.util.ArrayList;
import java.util.logging.*;

/**
 * Created by ryan on 11/23/15.
 */
public class Game
{
    private static final long TIME_PER_UPDATE = 15L;
    private Display display;
    private Input input;

    private World world;
    private Stats stats;

    public Game(Display display, Input input)
    {
        this.display = display;
        this.input = input;
        initGame();
        start();
    }

    private void initGame()
    {
        world = new World();
        stats = new Stats();
    }

    public void start()
    {
        this.initLevel();

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

            }

            display.updateActors(world.getPlayer(), world.getActors());
        }

    }

    public void step()
    {
        CommandState commandState = input.getPlayerCommandState();

        if(commandState.NEW_LEVEL)
        {
            world.resetCurrentLevel();
            this.initLevel();
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

        world.getPlayer().move(horizontalDirection, verticalDirection, world.getCurrentLevel());


        // Collect the gold!
        ArrayList<Actor> goldToRemove = new ArrayList<Actor>();
        for(Actor a : world.getActors())
        {
            if(a instanceof Item && ((Item)a).type == Item.Type.GOLD)
            {
                Item i = (Item)a;

                if(world.getPlayer().isCollidedWith(i))
                {
                    stats.playerGold++;
                    goldToRemove.add(a);
                    display.updateStats(stats);
                }
            }
        }
        world.getActors().removeAll(goldToRemove);

        //  Check for teleporters
        boolean onTeleporter = false;
        for(Actor a : world.getActors())
        {
            if(a instanceof Item && ((Item)a).type == Item.Type.TELEPORTER)
            {
                Item i = (Item)a;

                if(world.getPlayer().isCollidedWith(i))
                {
                    onTeleporter = true;
                    i.interact(world.getCurrentLevel(),world.getPlayer());
                    break;
                }
            }
        }

        if(!onTeleporter)
            world.getCurrentLevel().teleportersEnabled = true;

        //  Check for exit
        if(world.getPlayer().isCollidedWith(world.getExit()))
        {
            world.nextLevel();
            this.initLevel();
        }

        //  Move enemies
        for(Actor a : world.getActors())
        {
            if(a instanceof Enemy)
            {
                Enemy e = (Enemy) a;
                e.move(world.getCurrentLevel(),world.getPlayer());
            }
        }

    }

    private void initLevel()
    {
        display.initLevel(stats, world.getCurrentLevel());
    }
}
