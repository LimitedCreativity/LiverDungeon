package backend;

import backend.interfaces.Display;
import backend.interfaces.Input;

/**
 * Created by ryan on 11/23/15.
 */
public class Game
{
    private Display display;
    private Input input;

    public Game(Display display, Input input)
    {
        this.display = display;
        this.input = input;
    }
}
