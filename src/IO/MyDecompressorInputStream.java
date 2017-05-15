package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class represent a DecompressorOutputStream.
 * it uses the decorator design pattern on inputStream in order to Decompress a file into a Maze.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    /**
     * constructor for MyDecompressorInputStream.
     *
     * @param in is the Input Stream we are decorating.
     */
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int arrayIndex = 0;

        for(int i=0; i< b.length; i++){
            int nextByte = read();
            i++;
            int byteNumber = read();


            for(int j=0; j<byteNumber; j++){
                b[arrayIndex] = (byte) nextByte;
                arrayIndex++;
                if(arrayIndex == b.length) {

                    return i;
                }
            }

        }

        return 0;
    }
}
