package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;

//TODO: saving the solution to file with a unique proper file name.

/**
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze theMaze = (Maze) fromClient.readObject();

            File dir = new File("src/solution");
            if (!dir.exists() || !dir.isDirectory()) {
                dir.mkdir();
            }


            String filePath = "src/solution/" + (Arrays.toString(theMaze.toByteArray()).hashCode());
            File file = new File(filePath);
            if (file.exists() && !file.isDirectory()) {
                ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(filePath));
                Solution theMazeSolution = (Solution) fromFile.readObject();
                toClient.writeObject(theMazeSolution);
            } else {
                ObjectOutputStream toFile = new ObjectOutputStream(new FileOutputStream(filePath));
                toClient.flush();
                SearchableMaze searchableMaze = new SearchableMaze(theMaze);
                String searchingAlgorithmString = properties.getServerSolveMazeAlgo();
                ISearchingAlgorithm searchingAlgorithm = useSearch(searchingAlgorithmString);

                //TODO: delete this.
                System.out.println(searchingAlgorithm.getName());


                Solution theMazeSolution = searchingAlgorithm.solve(searchableMaze);
                toFile.writeObject(theMazeSolution);
                toClient.writeObject(theMazeSolution);
            }

        } catch (Exception e) {

        }
    }

    private ASearchingAlgorithm useSearch(String searchName) {
        ASearchingAlgorithm searchingAlgorithm;
        String searchingAlgo = properties.getServerSolveMazeAlgo();
        switch (searchingAlgo) {
            case "BreadthFirstSearch":
                searchingAlgorithm = new BreadthFirstSearch();
                break;
            case "DepthFirstSearch":
                searchingAlgorithm = new DepthFirstSearch();
                break;
            case "BestFirstSearch":
                searchingAlgorithm = new BestFirstSearch();
                break;
            default:
                throw new IllegalArgumentException("no such search algorithms");
        }
        return searchingAlgorithm;
    }
}
