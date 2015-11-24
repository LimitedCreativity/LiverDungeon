package frontend;

import backend.Level;
import backend.Tile;
import backend.actors.Actor;
import backend.actors.Player;
import backend.interfaces.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
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
    private static final int VIEW_WIDTH = 9, VIEW_HEIGHT = 9;

    //  Frame Constants
    private static final String FRAME_TITLE = "Liver Dungeon: Kill things";
    private static final int SCALE = 2;
    private static final int FRAME_WIDTH = VIEW_WIDTH*Tile.SIZE*SCALE,
            FRAME_HEIGHT = FRAME_WIDTH;
    private static final int FRAME_X = (SCREEN_WIDTH - FRAME_WIDTH) / 2,
            FRAME_Y = (SCREEN_HEIGHT - FRAME_HEIGHT) / 2;

    //  Images
    private static final String IMG_DIR = "img/";
    private BufferedImage levelImage, viewImage;
    private Graphics levelGraphics, viewGraphics;


    public Frame(Keyboard keyboard)
    {
        this.initFrame(keyboard);
    }

    private void initFrame(Keyboard keyboard)
    {
        this.setTitle(FRAME_TITLE);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyboard);
        this.setVisible(true);
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
        if(viewImage == null)
            return;

        g.drawImage(getScaledImage(viewImage),0,0,null);
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
        int finalh = src.getHeight()*SCALE, finalw = src.getHeight()*SCALE;
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
                String filepath = IMG_DIR + ( (tile.type == Tile.Type.WALL) ? "wall.png" : "floor.png");
                levelGraphics.drawImage(getImage(filepath),x*Tile.SIZE, y*Tile.SIZE, null);
            }
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
        int leftX = (x - (Tile.SIZE * (VIEW_WIDTH)/2 ) + 12);
        int topY = (y - (Tile.SIZE * (VIEW_HEIGHT)/2 ) + 12);
        int rightBoundary = levelImage.getWidth() - (VIEW_WIDTH * Tile.SIZE);
        int bottomBoundary = levelImage.getHeight() - (VIEW_HEIGHT * Tile.SIZE);

        if(leftX < 0)
            leftX = 0;
        if(topY < 0)
            topY = 0;

        if(leftX > rightBoundary)
            leftX = rightBoundary;
        if(topY > bottomBoundary)
            topY = bottomBoundary;

        //  Draw levelImage on viewImage
        BufferedImage levelSubImage = levelImage.getSubimage(leftX,topY,VIEW_WIDTH*Tile.SIZE,VIEW_HEIGHT*Tile.SIZE);
        viewGraphics.drawImage(levelSubImage,0,0,null);

        //  Filter viewable actors

        //  Draw viewable actors over viewImage
        viewGraphics.drawImage(getImage(IMG_DIR+"player.png"),x-leftX,y-topY,null);

        //  Repaint
        repaint();
    }
}
