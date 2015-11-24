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
        tiles = new Tile[height][width];
        
        for(int i=0; i<height; i++)
        {
            for(int j=0; j<width; j++)
            {
                //TEMP - fills the level with floor tiles surrounded by wall
                if(i == 0 || j == 0 || i == height-1 || j == width-1) 
                    tiles[i][j] = new Tile(Tile.Type.WALL);
                else
                    tiles[i][j] = new Tile(Tile.Type.FLOOR);
            }
        }
        
        actors = new ArrayList<>();
    }
    
    public ArrayList<Actor> getActors()
    {
        return actors;
    }
    
    public int getHeight()
    {
        return tiles.length;
    }
    
    public int getWidth()
    {
        return tiles[0].length;
    }
    
    public Tile getTile(int x, int y)
    {
        return tiles[y][x];
    }
}
