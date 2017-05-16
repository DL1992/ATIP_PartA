package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class is an object adapter class.
 * serachble maze is a searching problem of the maze domain.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}
