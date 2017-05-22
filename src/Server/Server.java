package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//TODO: Check connection ot server (must be on/off). creating thread pool. dealing with server termination.

/**
 * This class is an object adapter class.
 * serachble maze is a searching problem of the maze domain.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        new Thread(() -> runServer()).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            server.setSoTimeout(this.listeningInterval);
            while (!this.stop) {
                Socket client = server.accept();
                new Thread(() -> handleClient(client)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket client) {
        try {
            System.out.println("Client accepted! yay!");
            System.out.println(String.format("Handling client with socket: %s", client.toString()));
            this.serverStrategy.serverStrategy(client.getInputStream(), client.getOutputStream());
            client.getInputStream().close();
            client.getOutputStream().close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        System.out.println("the server has crashed :/");
        this.stop = true;
    }
}

