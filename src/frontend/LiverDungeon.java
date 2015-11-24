package frontend;

import backend.Game;

/**
 * Created by ryan on 11/23/15.
 */
public class LiverDungeon
{
    public static void main(String args[])
    {
        Keyboard keyboard = new Keyboard();
        Frame frame = new Frame(keyboard);
        Game game = new Game(frame, keyboard);
    }
}
