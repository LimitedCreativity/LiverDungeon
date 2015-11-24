package backend;

import backend.actors.Actor;
import java.util.ArrayList;

/**
 * Created by ryan on 11/23/15.
 */
public class Level
{
    private final Tile[][] tiles;
    private final ArrayList<Actor> actors;
    
    public Level(int width, int height)
    {
        tiles = new Tile[width][height];
        
        generateRandomLevel();
        
        actors = new ArrayList<>();
    }
    
    private void generateRandomLevel()
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
}
