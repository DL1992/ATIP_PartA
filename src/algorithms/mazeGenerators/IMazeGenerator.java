package algorithms.mazeGenerators;

/**
 * Created by user on 11/04/2017.
 */
public interface IMazeGenerator {

    Maze generate(int numOfRows, int numOfCols);

    long measureAlgorithmTimeMillis(int numOfRows, int numOfCols);
}
