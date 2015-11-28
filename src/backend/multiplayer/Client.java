package backend.multiplayer;

import frontend.Frame;
import frontend.Keyboard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ryan on 11/28/15.
 */
public class Client
{


    private ClientState state;

    public Client(String ip)
    {
//        Keyboard keyboard = new Keyboard();
//        Frame frame = new Frame(keyboard);

        this.state = ClientState.READY;
        this.initConnection(ip);

    }

    private void initConnection(String ip)
    {
        try
        {
            //  Initialize sockets
            Socket toServer = new Socket(ip,1234);
            Socket fromServer = new Socket(ip,5678);

            //  Initialize streams
            ObjectInputStream fromServerStream = new ObjectInputStream(fromServer.getInputStream());
            ObjectOutputStream toServerStream = new ObjectOutputStream(toServer.getOutputStream());

            //  Begin client loop
            while(true)
            {
                step(fromServerStream,toServerStream);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void step(ObjectInputStream fromServerStream, ObjectOutputStream toServerStream) throws IOException, ClassNotFoundException
    {
        //  State-based logic
        switch (state)
        {
            case READY:
                //  Waiting for server confirmation
                ServerState serverState = (ServerState)fromServerStream.readObject();
                if(serverState == ServerState.INITIALIZING)
                {
                    System.out.println("Server state is initializing!");
                    this.state = ClientState.INITIALIZING;
                }
                break;
            case INITIALIZING:
                //  Loading level
                System.out.println("Made it to initialization");
                System.exit(0);
                break;
            case SENDING_COMMANDS:
                //  Reading input and sending to server
                break;
            case RECEIVING_GAME_DATA:
                //  Waiting for game data for redraw
                break;
        }
    }
}
