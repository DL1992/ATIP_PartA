package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class is the Interface for a client strategy.
 * the purpose of a a client strategy is to tell the client how to work while working with servers.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface IClientStrategy {

    /**
     * a strategy of a client decide how it will work with a server.
     * what it is sending to the server and what does it expects to get back from the server.
     * also what to do with the server response.
     * the strategy should decide if the connection with a server is consistent or QA.
     *
     * @param inFromServer the stream the client gets from a server ( the server outputStream).
     * @param outToServer  the stream the client use to send data to the server (the server inputStream).
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}