package algorithms.mazeGenerators;

/**
 * This class is abstract class of a maze generator.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int numOfRows, int numOfCols);

    @Override
    public long measureAlgorithmTimeMillis(int numOfRows, int numOfCols) {
        long lstartTime = System.currentTimeMillis();
        generate(numOfRows, numOfCols); // We don't care about catching the generated maze just how long it took to generate.
        long lendTime = System.currentTimeMillis();
        return lendTime - lstartTime;
    }

    /**
     * Set the maze to include only walls.
     * this is used to init a int[][] grid for a maze.
     *
     * @param data the grid of the maze as a int[][]
     */
    protected void initData(int[][] data) {
        if (null != data) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] = 1;
                }
            }
        }
    }

    /**
     * Creates a legal(inside the maze) random Position in a given maze with row*cols setting.
     * Helper function for creating a start and goal position in any class that is building a maze.
     *
     * @param numOfRows the number of rows in the maze
     * @param numOfCols the number of cols in the maze
     * @return a new Position representing a position in a maze
     */
    protected Position createPosition(int numOfRows, int numOfCols) {
        int positionRowIndex = (int) (Math.random() * (numOfRows - 1));
        int positionColIndex = (int) (Math.random() * (numOfCols - 1));
        return new Position(positionRowIndex, positionColIndex);
    }
}
