package frontend;

import backend.Game;
import backend.multiplayer.Client;
import backend.multiplayer.Server;

import java.util.Scanner;

/**
 * Created by ryan on 11/23/15.
 */
public class LiverDungeon
{
    private static final boolean MULTIPLAYER = false;

    public static void main(String args[])
    {
        if(MULTIPLAYER)
            startMultiplayerGame();
        else
            startSingleplayerGame();
    }

    private static void startSingleplayerGame()
    {
        Keyboard keyboard = new Keyboard();
        Frame frame = new Frame(keyboard);
        Game game = new Game(frame, keyboard);
    }

    private static void startMultiplayerGame()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Are you the server (y/n)? ");
        boolean isServer = (scanner.nextLine().equalsIgnoreCase("y"));

        if(isServer)
        {
            System.out.println("Starting server");

            new Server();
        }
        else
        {
            System.out.print("What is the server ip? ");
            String ip = scanner.nextLine();

            new Client(ip);
        }

    }
}
