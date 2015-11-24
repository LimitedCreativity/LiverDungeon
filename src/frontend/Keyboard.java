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
//        CommandState temp = cmdState;
//
//        cmdState = new CommandState();

        return cmdState;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        setCommand(keyEvent,true);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent){
        setCommand(keyEvent,false);
    }

    public void setCommand(KeyEvent keyEvent, boolean value)
    {
        switch(keyEvent.getKeyCode())
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                cmdState.MOVE_UP = value;
                return;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                cmdState.MOVE_DOWN = value;
                return;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                cmdState.MOVE_LEFT = value;
                return;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                cmdState.MOVE_RIGHT = value;
                return;
        }
    }
}
