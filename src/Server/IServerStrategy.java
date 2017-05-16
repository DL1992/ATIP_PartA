package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sergayen on 5/16/2017.
 */
public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}
