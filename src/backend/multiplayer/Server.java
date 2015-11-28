package backend.multiplayer;

import backend.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ryan on 11/28/15.
 */
public class Server
{
    private ServerState state;

    public Server()
    {
        //  Game game = new Game(/* now needs array of inputs and displays */)

        this.state = ServerState.READY;
        this.init();
    }

    private void init()
    {

        try
        {
            //  Initialize sockets
            ServerSocket toClient = new ServerSocket(5678);
            ServerSocket fromClient = new ServerSocket(1234);

            System.out.print("Waiting for client connection...");
            Socket tcSocket, fcSocket;

            tcSocket = toClient.accept();
            fcSocket = fromClient.accept();

            System.out.println(" CONNECTED!");

            //  Initialize streams
            ObjectInputStream fromClientStream = new ObjectInputStream(fcSocket.getInputStream());
            ObjectOutputStream toClientStream = new ObjectOutputStream(tcSocket.getOutputStream());

            //  Begin client loop
            while(true)
            {
                step(fromClientStream,toClientStream);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void step(ObjectInputStream fromClientStream, ObjectOutputStream toClientStream) throws IOException
    {
        switch (state)
        {
            case READY:
                this.state = ServerState.INITIALIZING;
                System.out.println("Sending server state to client...");
                toClientStream.writeObject(state);
                break;
            case INITIALIZING:
                break;

        }
    }
}
