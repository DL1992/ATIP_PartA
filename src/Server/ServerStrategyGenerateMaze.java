package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

//TODO: saving the compressed maze to file with a unique proper file name.

/**
 * Created by sergayen on 5/16/2017.
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeSizes = (int[]) fromClient.readObject();
            toClient.flush();

            String mazeFileName = "a";
            Maze clientMaze = new MyMazeGenerator().generate(mazeSizes[0], mazeSizes[1]);
            byte[] byteClientMaze = clientMaze.toByteArray();
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(byteClientMaze);
            toClient.writeObject(byteClientMaze);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
