package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}