package frontend;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ryan on 12/21/15.
 */
public class Hud
{
    //  0123456789ABCDEF
    //   HHHHHHHH  GGGG

    //  HUD Properties
    private static final int HUD_HEIGHT = 25, HUD_WIDTH = 400, PADDING_TOP = 5, CONTENT_HEIGHT = 15;
    private static final Color BG_COLOR = Color.GRAY;
    private BufferedImage hudImage;
    private Graphics hudGraphics;

    //  Player Health
    private static final int HEALTH_X = 25, HEALTH_WIDTH = 200;
    private static final Color HEALTH_COLOR = Color.BLUE,
            HEALTHBAR_COLOR = new Color(77, 121, 255);
    private int maxHealth, health;

    //  Gold
    private static final int GOLD_X = 275, GOLD_WIDTH = 100;
    private int gold;

    public Hud()
    {
        this.maxHealth = 1;
        this.health = 0;
        this.gold = 0;
        this.hudImage = new BufferedImage(HUD_WIDTH, HUD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.hudGraphics = hudImage.getGraphics();

        renderImage();
    }

    private void renderImage()
    {
        hudGraphics.setColor(BG_COLOR);
        hudGraphics.fillRect(0, 0, HUD_WIDTH,HUD_HEIGHT);

        hudGraphics.setColor(HEALTHBAR_COLOR);
        hudGraphics.fillRect(HEALTH_X, PADDING_TOP, HEALTH_WIDTH, CONTENT_HEIGHT);

        hudGraphics.setColor(HEALTH_COLOR);
        hudGraphics.fillRect(HEALTH_X, PADDING_TOP, HEALTH_WIDTH*health/maxHealth, CONTENT_HEIGHT);

        hudGraphics.setColor(Color.WHITE);
        hudGraphics.setFont(new Font("Arial",Font.BOLD,CONTENT_HEIGHT));
        hudGraphics.drawString("Gold: " + this.gold, GOLD_X, CONTENT_HEIGHT + PADDING_TOP);
    }

    public void setHealth(int health, int maxHealth)
    {
        this.health = health;
        this.maxHealth = maxHealth;
        renderImage();
    }

    public void setGold(int gold)
    {
        this.gold = gold;
        renderImage();
    }

    public BufferedImage getHudImage()
    {
        return hudImage;
    }




}
