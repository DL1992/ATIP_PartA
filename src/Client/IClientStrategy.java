package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by user on 16/05/2017.
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
