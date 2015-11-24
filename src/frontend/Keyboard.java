package frontend;

import backend.CommandState;
import backend.interfaces.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ryan on 11/23/15.
 */
public class Keyboard implements Input, KeyListener
{
    private CommandState cmdState;

    public Keyboard()
    {
        cmdState = new CommandState();
    }

    @Override
    public CommandState getPlayerCommandState()
    {
        CommandState retval = cmdState;

        cmdState = new CommandState();

        return retval;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        switch(keyEvent.getKeyCode())
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                cmdState.MOVE_UP = true;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                cmdState.MOVE_DOWN = true;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                cmdState.MOVE_LEFT = true;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                cmdState.MOVE_RIGHT = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent){

    }
}
