package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * This class represents a working server.
 * the server is using a thread pool in order to handle different clients in the same time.
 * server works according to a specific strategy.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private ExecutorService executor;
    private volatile boolean stop;

    /**
     * constructor for a server.
     *
     * @param port              the port clients can connect to the server with.
     * @param listeningInterval the time pass between a server act on a call from a client.
     * @param serverStrategy    the strategy the server is working by.
     */
    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    /**
     * This function start the server in a new thread so it will work in the background.
     * init the thread pool with the right amount according to the properties file.
     */
    public void start() {
        this.executor = Executors.newFixedThreadPool(Integer.parseInt(properties.getServerThreadPoolCount()));
        new Thread(() -> runServer()).start();
    }

    /**
     * The main function of a server.
     * as long as the server is up (not stopped) the server will handle clients.
     * each client will be handle in a new thread according to the thread pool.
     */
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

    /**
     * main function for the server, the function is in charge of handle the clients according to the server strategy.
     * the connection is close in the end of each handle.
     * the server is working in a QA manner.
     *
     * @param client the client the server is working in front right now.
     */
    private void handleClient(Socket client) {
        try {
            System.out.println(String.format("Handling a client with socket: %s", client.toString()));
            this.serverStrategy.serverStrategy(client.getInputStream(), client.getOutputStream());
            client.getInputStream().close();
            client.getOutputStream().close();
            client.close();
        } catch (Exception e) {

        }
    }

    /**
     * this function stops the server. he will not get any more new clients and the thread pool will close.
     */
    public void stop() {
        this.stop = true;
        System.out.println("The server did its job - and therefore it dies now");
        this.executor.shutdown();
    }
}

