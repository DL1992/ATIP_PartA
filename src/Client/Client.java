package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * this class represents a Client for a server.
 * client can communicate with different servers in different ways.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Client {
    InetAddress IP;
    int port;
    IClientStrategy clientStrategy;

    /**
     * constructor for Client.
     *
     * @param IP             the IP address of the server.
     * @param port           the port of the client will connect to the server.
     * @param clientStrategy the client strategy.
     */
    public Client(InetAddress IP, int port, IClientStrategy clientStrategy) {
        this.IP = IP;
        this.port = port;
        this.clientStrategy = clientStrategy;
    }

    /**
     * creates a connection with a server and active the client strategy on the server.
     * there must be a correlation between clients and servers strategy.
     */
    public void communicateWithServer() {
        try {
            Socket theServer = new Socket(this.IP, this.port);
            System.out.println();
            System.out.println("Successfully connected to server");
            this.clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
