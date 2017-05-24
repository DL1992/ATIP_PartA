package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;

import java.io.*;


/**
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeSizes = (int[]) fromClient.readObject();
            toClient.flush();

            String mazeGeneratorAlgo = properties.getServerMazeGenerateAlgo();
            AMazeGenerator mazeGenerator = useMazeGenerator(mazeGeneratorAlgo);
            
            Maze clientMaze = mazeGenerator.generate(mazeSizes[0], mazeSizes[1]);
            byte[] byteClientMaze = clientMaze.toByteArray();
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            OutputStream out = new MyCompressorOutputStream(byteOut);
            out.write(byteClientMaze);
            toClient.writeObject(byteOut.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private AMazeGenerator useMazeGenerator(String mazeGeneratorAlgo) {
        AMazeGenerator mazeGenerator;
        switch (mazeGeneratorAlgo) {
            case "MyMazeGenerator":
                mazeGenerator = new MyMazeGenerator();
                break;
            case "SimpleMazeGenerator":
                mazeGenerator = new SimpleMazeGenerator();
                break;
            default:
                throw new IllegalArgumentException("no maze Generator Method");
        }
        return mazeGenerator;
    }
}
