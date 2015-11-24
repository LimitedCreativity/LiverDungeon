package backend;

import backend.actors.Actor;
import backend.actors.Item;
import backend.actors.Player;

import java.util.ArrayList;

/**
 * Created by ryan on 11/23/15.
 */
public class Level
{
    private Tile[][] tiles;
    private ArrayList<Actor> actors;

    public Level()
    {
        int minSize = 16, maxSize = 16, diff = maxSize - minSize;
        int randW = (int)(Math.random() * diff) + minSize;
        int randH = (int)(Math.random() * diff) + minSize;

        initLevel(randW, randH);
    }

    public Level(int width, int height)
    {
        initLevel(width, height);
    }

    private void initLevel(int width, int height)
    {
        tiles = new Tile[width][height];
        actors = new ArrayList<>();

        generateRandomLevel();
    }

    private void generateRandomLevel()
    {
        generateBoundaryLevel();

        int rand;
        int chanceWall = 25;

        for(int x = 1; x < getWidth() - 1; x++)
        {
            for(int y = 1; y < getHeight() - 1; y++)
            {
                rand = (int)(Math.random()*100);
                if(rand < chanceWall)
                    tiles[x][y].type = Tile.Type.WALL;
            }
        }

        scatterGold();
        addPlayer();
        addExit();
    }
    
    private void generateBoundaryLevel()
    {
        for(int i=0; i<getWidth(); i++)
        {
            for(int j=0; j<getHeight(); j++)
            {
                //TEMP - fills the level with floor tiles surrounded by wall
                if(i == 0 || j == 0 || i == getWidth()-1 || j == getHeight()-1) 
                    tiles[i][j] = new Tile(Tile.Type.WALL);
                else
                    tiles[i][j] = new Tile(Tile.Type.FLOOR);
            }
        }
    }

    private void scatterGold()
    {
        int numGold = this.getWidth()*this.getHeight()/100;

        for(int i = 0; i < numGold; i++)
            actors.add(new Item(getEmptyLocation(), Item.Type.GOLD));
    }

    private void addPlayer()
    {
        actors.add(new Player(getEmptyLocation(), "Player"));
    }

    private void addExit()
    {
        actors.add(new Item(getEmptyLocation(), Item.Type.EXIT));
    }

    private Location getEmptyLocation()
    {
        int randX = 0, randY = 0;

        while(!isTileEmpty(randX, randY))
        {
            randX = (int)(Math.random()*this.getWidth());
            randY = (int)(Math.random()*this.getHeight());
        }

        return new Location(randX*Tile.SIZE,randY*Tile.SIZE);
    }

    //  Temporary garbage
    public Player getPlayer()
    {
        for(Actor a : actors)
        {
            if(a instanceof Player)
                return (Player)a;
        }

        return null;
    }

    public Item getExit()
    {
        for(Actor a : actors)
        {
            if(a instanceof Item && ((Item)a).type == Item.Type.EXIT)
                return (Item)a;
        }

        return null;
    }

    public ArrayList<Actor> getActors()
    {
        return actors;
    }
    
    public int getHeight()
    {
        return tiles[0].length;
    }
    
    public int getWidth()
    {
        return tiles.length;
    }
    
    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

    public Item getItem(int x, int y)
    {
        for(Actor a : actors)
        {
            if(a instanceof Item)
            {
                Item i = (Item)a;

                if (i.getX() == x / Tile.SIZE && i.getY() == y / Tile.SIZE)
                    return i;
            }
        }

        return null;
    }

    public boolean isTileEmpty(int x, int y)
    {
        Tile tile = this.getTile(x, y);
        Item item = this.getItem(x, y);

        return (tile.type == Tile.Type.FLOOR && item == null);
    }
}
