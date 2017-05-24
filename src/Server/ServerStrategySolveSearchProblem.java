package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

//TODO: saving the solution to file with a unique proper file name.

/**
 *
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
            String filePath = theMaze.toByteArray().toString();
            File f = new File(filePath);
            if(f.exists() && !f.isDirectory()){
                ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(filePath));
                Solution theMazeSolution = (Solution) fromFile.readObject();
                toClient.writeObject(theMazeSolution);
            }
            else {
                ObjectOutputStream toFile = new ObjectOutputStream(new FileOutputStream(filePath));
                toClient.flush();
                SearchableMaze searchableMaze = new SearchableMaze(theMaze);
                ISearchingAlgorithm searchingAlgorithm = new BestFirstSearch();
                Solution theMazeSolution = searchingAlgorithm.solve(searchableMaze);
                toFile.writeObject(theMazeSolution);
                toClient.writeObject(theMazeSolution);
            }
            toClient.flush();

            SearchableMaze searchableMaze = new SearchableMaze(theMaze);

            ISearchingAlgorithm searchingAlgorithm;
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
            System.out.println(searchingAlgorithm.getName());
            Solution theMazeSolution = searchingAlgorithm.solve(searchableMaze);
//            toFile.writeObject(theMazeSolution);
            toClient.writeObject(theMazeSolution);
            System.out.println("something in the way");

        } catch (Exception e) {

        }
    }
}
