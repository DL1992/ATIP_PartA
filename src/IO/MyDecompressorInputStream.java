package IO;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class represent a DecompressorOutputStream.
 * it uses the decorator design pattern on inputStream in order to Decompress a Maze.
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
        return this.in.read();
    }

    @Override
    /**
     * fills the byteArray with decompressed data from out InoutStream
     * using our pre-agreed decompression technique
     *
     * @param byteArray is the byte array we are filling.
     */
    public int read(byte[] byteArray) throws IOException {
        int arrayIndex = 0;
        int nextByte = read();
        while (nextByte != -1) {
            int byteNumber = read();
            if (byteNumber == -1) {
                return arrayIndex;
            }
            for (int j = 0; j < byteNumber; j++) {
                byteArray[arrayIndex] = (byte) nextByte;
                arrayIndex++;
            }
            nextByte = read();
            if (nextByte == -1) {
                return arrayIndex;
            }
        }
        return arrayIndex;
    }
}
