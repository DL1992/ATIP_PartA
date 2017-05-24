package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class is the Interface for a server strategy.
 * the purpose of a a server strategy is to tell a server how to work with clients.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface IServerStrategy {
    /**
     * a strategy of a server decide how it will work with a client.
     * what it is sending to the the client and what does it expects to get from the client.
     * the strategy should decide if the connection with a client is consistent or QA.
     *
     * @param inFromClient the inputStream of the server (the client OutputStream).
     * @param outToClient  the outputStream of the server (the client inputStream).
     */
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}
