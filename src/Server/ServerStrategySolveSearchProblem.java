package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;


/**
 * This server strategy solves a search problem the client provides.
 * using the searching algorithm provided in the properties file.
 * it also checks if a solution for this maze already exists - and returns it if it does.
 *
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

            // creates the solution dir if it doesn't exist
            File dir = new File("src/Solution Bag");
            if (!dir.exists() || !dir.isDirectory()) {
                dir.mkdirs();
            }
            String filePath = "src/Solution Bag/" + (Arrays.toString(theMaze.toByteArray()).hashCode());
            File file = new File(filePath);

            //if a solution exists - return it;
            // otherwise - solve the search problem given using the searching algorithm provided - add it to the solution bag and return it
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
                Solution theMazeSolution = searchingAlgorithm.solve(searchableMaze);
                toFile.writeObject(theMazeSolution);
                toClient.writeObject(theMazeSolution);
            }

        } catch (Exception e) {

        }
    }

    /**
     * this is a helper function to serverStrategy
     * creates and returns the required searching algorithm specified in the properties file
     *
     * @param searchName the name of the specified searching algorithm
     * @return the searching algorithm specified in the properties file
     */
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
                throw new IllegalArgumentException("The search algorithm you required was not defined");
        }
        return searchingAlgorithm;
    }
}
