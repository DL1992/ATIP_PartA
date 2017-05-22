package IO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class represent a CompressorOutputStream.
 * it uses the decorator design pattern on outputStream in order to compress mazes to a file.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int lastByte;
    private int byteCount;
    private boolean isLastByte;
    private boolean isFirstByte;

    /**
     * constructor for MyCompressorOutputStream.
     *
     * @param out is the outputStream we are decorating.
     */
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.byteCount = 0;
        this.isLastByte = true;
        this.isFirstByte = true;
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < b.length - 1; i++) {
            this.isLastByte = false;
            write(b[i]);
        }
        this.isLastByte = true;
        write(b[b.length - 1]);

    }

    /**
     * return the compressor to it's initial settings after reading an array is done.
     */
    private void resetCompressor() {
        this.byteCount = 0;
        this.isLastByte = true;
        this.isFirstByte = true;
    }

    @Override
    /**
     * the way we compress a bit is by counting the number of times he cane in a row and then writing the int
     * + the number of times.
     * we write only  when the current int is ont like the last one.
     *
     */
    public void write(int b) throws IOException {
        if (this.isFirstByte) {
            this.byteCount++;
            this.lastByte = b;
            this.isFirstByte = false;
        } else {
            if (this.lastByte == b) {
                this.byteCount++;
            } else {
                this.out.write(this.lastByte);
                this.out.write(this.byteCount);
                this.byteCount = 1;
                this.lastByte = b;
            }
            if (this.byteCount == 127) {
                this.out.write(this.lastByte);
                this.out.write(this.byteCount);
                this.byteCount = 0;
                this.isFirstByte = true;
            }
            if (this.isLastByte) {
                this.out.write(b);
                this.out.write(this.byteCount);
                resetCompressor();
            }
        }
    }


}
