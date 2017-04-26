package algorithms.mazeGenerators;

/**
 * This class generate a random maze.
 * it extends the abstract Class AMazeGenerator
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * generate a completely random maze.
     * this is done by starting with a grid full of walls, randomly deciding a start and goal position,
     * creates a straight path between them and then randomly breaking walls.
     */
    @Override
    public Maze generate(int numOfRows, int numOfCols) {
        if (numOfRows > 0 && numOfCols > 0) {
            if (!(numOfRows == 1 && numOfCols == 1)) {
                Maze maze = new Maze();
                int[][] data = new int[numOfRows][numOfCols];
                initData(data); //init all the cells to walls
                maze.setData(data);
                SetPositions(maze, numOfRows, numOfCols);
                setMazeData(data, maze);
                return maze;
            }
        }
        return null;
    }

    /**
     * creates a random data grid for the maze.
     * helper function for generate.
     *
     * @param data the grid of the maze we are working on.
     * @param maze the maze which will use the data as it's grid
     */
    private void setMazeData(int[][] data, Maze maze) {
        makePath(data, maze.getStartPosition(), maze.getGoalPosition());
        randomizeData(data);
        maze.setData(data);
    }

    /**
     * randomly breaks walls in the maze. add 0-number of cols of paths in each row.
     * helper function for setMazeData.
     *
     * @param data is the grid of the maze.
     */
    private void randomizeData(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            int numOfPathsInRow = (int) (Math.random() * (data[i].length - 1));
            for (int j = 0; j < numOfPathsInRow; j++) {
                int pathInCol = (int) (Math.random() * (data[i].length - 1));
                data[i][pathInCol] = 0;
            }
        }
    }

    /**
     * Makes a path in the maze between the start and goal position to make sure the random maze has a solution.
     * helper function for setMazeData.
     *
     * @param data          the grid of the maze.
     * @param startPosition the start Position of The Maze.
     * @param goalPosition  the goal position of the Maze.
     */
    private void makePath(int[][] data, Position startPosition, Position goalPosition) {
        data[startPosition.getRowIndex()][startPosition.getColumnIndex()] = 0;
        data[goalPosition.getRowIndex()][goalPosition.getColumnIndex()] = 0;
        if (startPosition.getRowIndex() < goalPosition.getRowIndex()) {
            for (int i = startPosition.getRowIndex(); i < goalPosition.getRowIndex(); i++) {
                data[i][startPosition.getColumnIndex()] = 0;
            }
        } else {
            for (int i = startPosition.getRowIndex(); i > goalPosition.getRowIndex(); i--) {
                data[i][startPosition.getColumnIndex()] = 0;
            }
        }
        if (startPosition.getColumnIndex() < goalPosition.getColumnIndex()) {
            for (int j = startPosition.getColumnIndex(); j < goalPosition.getColumnIndex(); j++) {
                data[goalPosition.getRowIndex()][j] = 0;
            }
        } else {
            for (int j = startPosition.getColumnIndex(); j > goalPosition.getColumnIndex(); j--) {
                data[goalPosition.getRowIndex()][j] = 0;
            }
        }
    }

    /**
     * this function Creates and sets the start and goal position of the maze randomly.
     * helper function for generate.
     *
     * @param maze      the Maze we set its start and goal positions.
     * @param numOfRows the number of rows in the maze
     * @param numOfCols the number of col in the maze
     */
    private void SetPositions(Maze maze, int numOfRows, int numOfCols) {
        do {
            maze.setStartPosition(createPosition(numOfRows, numOfCols));
        } while (null == maze.getStartPosition());
        do {
            maze.setGoalPosition(createPosition(numOfRows, numOfCols));
        } while (null == maze.getGoalPosition());
    }
}
