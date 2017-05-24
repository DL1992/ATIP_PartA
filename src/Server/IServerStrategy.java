package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}
