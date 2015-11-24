package frontend;

import backend.Level;
import backend.Tile;
import backend.actors.Actor;
import backend.actors.Item;
import backend.actors.Player;
import backend.interfaces.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ryan on 11/23/15.
 */
public class Frame extends JFrame implements Display
{
    //  Display information
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width,
            SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    //  View constants
    private static final int VIEW_WIDTH = 16, VIEW_HEIGHT = 9,
            PIXEL_WIDTH = VIEW_WIDTH * Tile.SIZE,
            PIXEL_HEIGHT = VIEW_HEIGHT * Tile.SIZE;

    //  Frame Constants
    private static final String FRAME_TITLE = "Liver Dungeon: Kill things";
    private static final int SCALE = getScreenScale()-1;
    private static final int FRAME_WIDTH = VIEW_WIDTH*Tile.SIZE*SCALE,
            FRAME_HEIGHT = VIEW_HEIGHT*Tile.SIZE*SCALE;
    private static final int FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH) / 2,
            FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT) / 2;
    private static final boolean FRAME_UNDECORATED = true;

    //  Images
    private static final String IMG_DIR = "img/", ITEM_DIR = "items/", ENEMY_DIR = "enemies/", TILE_DIR = "tiles/";
    private BufferedImage levelImage, viewImage, scaledImage;
    private Graphics levelGraphics, viewGraphics;


    public Frame(Keyboard keyboard)
    {
        this.initFrame(keyboard);
    }

    private void initFrame(Keyboard keyboard)
    {
        this.setTitle(FRAME_TITLE);
        this.setResizable(false);
        this.setUndecorated(FRAME_UNDECORATED);
        this.setLayout(new FlowLayout());
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyboard);
        this.setVisible(true);
    }

    private static int getScreenScale()
    {
        if(SCREEN_WIDTH < SCREEN_HEIGHT)
            return SCREEN_WIDTH / VIEW_WIDTH / Tile.SIZE;
        else return SCREEN_HEIGHT / VIEW_HEIGHT / Tile.SIZE;
    }

    private void initViewImage()
    {
        //  Init the view image
        viewImage = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        viewGraphics = viewImage.getGraphics();
    }

    private void initLevelImage(int width, int height)
    {
        levelImage = new BufferedImage(width*Tile.SIZE, height*Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
        levelGraphics = levelImage.getGraphics();
    }

    public void paint(Graphics g)
    {
        if(scaledImage == null)
            return;

        g.drawImage(scaledImage,0,0,null);
    }

    private BufferedImage getImage(Actor a)
    {
        String filepath = IMG_DIR;

        if(a instanceof Player)
            filepath += "player.png";
        else if(a instanceof Item)
        {
            Item i = (Item) a;
            filepath += ITEM_DIR;

            if(i.type == Item.Type.EXIT)
                filepath += "exit.png";
            else if(i.type == Item.Type.GOLD)
                filepath += "gold.png";
        }
        else
        {
            filepath += "unknown.png";
        }

        return getImage(filepath);

    }

    private BufferedImage getImage(String file)
    {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private BufferedImage getScaledImage(BufferedImage src)
    {
        int finalh = src.getHeight()*SCALE, finalw = src.getWidth()*SCALE;
        BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(src, 0, 0, finalw, finalh, null);
        g2.dispose();
        return resizedImg;
    }

    @Override
    public void initLevel(Level level)
    {
        initLevelImage(level.getWidth(), level.getHeight());

        for(int y = 0; y < level.getHeight(); y++)
            for(int x = 0; x < level.getWidth(); x++)
            {
                Tile tile = level.getTile(x,y);
                String filepath = IMG_DIR + TILE_DIR + ( (tile.type == Tile.Type.WALL) ? "wall.png" : "floor.png");
                levelGraphics.drawImage(getImage(filepath),x*Tile.SIZE, y*Tile.SIZE, null);
            }

        this.setTitle("Liver Dungeon: " + level.getPlayer().name);
    }

    @Override
    public void updateActors(Player player, ArrayList<Actor> actors)
    {
        //  Ensure viewImage is initialized
        if(viewImage == null)
            initViewImage();

        //  Get player's location
        int x = player.getX();
        int y = player.getY();

        //  Calculate coordinates in levelImage to grab
        int leftX = (x - (PIXEL_WIDTH - Tile.SIZE)/2 );
        int topY = (y - (PIXEL_HEIGHT - Tile.SIZE)/2 );
        int rightBoundary = levelImage.getWidth() - PIXEL_WIDTH;
        int bottomBoundary = levelImage.getHeight() - PIXEL_HEIGHT;

        if(leftX < 0)
            leftX = 0;
        if(topY < 0)
            topY = 0;

        if(leftX > rightBoundary)
            leftX = rightBoundary;
        if(topY > bottomBoundary)
            topY = bottomBoundary;

        //  Draw levelImage on viewImage
        BufferedImage levelSubImage = levelImage.getSubimage(leftX,topY,PIXEL_WIDTH, PIXEL_HEIGHT);
        viewGraphics.drawImage(levelSubImage,0,0,null);

        //  Filter visible actors
        int rightX = leftX + PIXEL_WIDTH;
        int bottomY = topY + PIXEL_HEIGHT;

        ArrayList<Actor> visibleActors = new ArrayList<Actor>();

        for(Actor a : actors)
        {
            if(a.getX() > leftX - Tile.SIZE && a.getX() < rightX &&
                    a.getY() > topY - Tile.SIZE && a.getY() < bottomY)
                visibleActors.add(a);
        }

        visibleActors.add(player);

        //  Draw viewable actors over viewImage
        for(Actor a : visibleActors)
        {
            viewGraphics.drawImage(getImage(a), a.getX() - leftX, a.getY() - topY, null);
        }

        scaledImage = getScaledImage(viewImage);

        //  Repaint
        repaint();
    }
}
