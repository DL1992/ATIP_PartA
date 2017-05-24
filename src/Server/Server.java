package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//TODO: Check connection ot server (must be on/off). creating thread pool. dealing with server termination.

/**
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private ExecutorService executor;
    private volatile boolean stop;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        this.executor = Executors.newFixedThreadPool(Integer.parseInt(properties.getServerThreadPoolCount()));
        new Thread(() -> runServer()).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            server.setSoTimeout(this.listeningInterval);
            while (!this.stop) {
                try {
                    Socket client = server.accept();
                    this.executor.execute(() -> handleClient(client));
                } catch (SocketTimeoutException e) {
//                    System.out.println("SocketTimeout!");
                }
            }
            server.close();
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
        } catch (Exception e) {

        }
    }

    public void stop() {
        this.stop = true;
        System.out.println("the server has crashed :/");
        this.executor.shutdown();
    }
}

