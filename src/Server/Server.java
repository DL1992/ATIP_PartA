package Server;

import java.io .IOException;
import java.net.ServerSocket;

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

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            server.setSoTimeout(this.listeningInterval);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {

    }
}

