package algorithms.mazeGenerators;

/**
 * This class is the Interface for a maze generator.
 * the purpose of a maze generator is to generate a random maze.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface IMazeGenerator {

    /**
     * Generate a random maze.
     * A maze should be a grid of rows and cols.
     * A maze should have a start position and a goal position and at least one soultion(a way to get from start to goal).
     *
     * @param numOfRows the number of rows in the maze.
     * @param numOfCols the number of columns in the maze.
     * @return a random generated Maze.
     */
    Maze generate(int numOfRows, int numOfCols);

    /**
     * Measure the time that it took to generate a random maze using the generate function.
     * In mills.
     *
     * @param numOfRows the number of rows in the maze.
     * @param numOfCols the number of columns in the maze.
     * @return how much time it took the function generate to create the random maze using the params.
     */
    long measureAlgorithmTimeMillis(int numOfRows, int numOfCols);
}
