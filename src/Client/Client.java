package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Client {
    InetAddress IP;
    int port;
    IClientStrategy clientStrategy;

    public Client(InetAddress IP, int port, IClientStrategy clientStrategy) {
        this.IP = IP;
        this.port = port;
        this.clientStrategy = clientStrategy;
    }

    //might be name start instead
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
