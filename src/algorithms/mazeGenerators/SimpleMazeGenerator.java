package algorithms.mazeGenerators;

/**
 * Created by user on 11/04/2017.
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int numOfRows, int numOfCols) {
        Maze maze = new Maze();
        int[][] data = new int[numOfRows][numOfCols];
        initData(data); //init all the cells to walls
        maze.setData(data);
        SetPositions(maze, numOfRows, numOfCols);
        setMazeData(data, maze);
        return maze;
    }

    private void setMazeData(int[][] data, Maze maze) {
        makePath(data, maze.getStartPosition(), maze.getGoalPosition());
        randomizeData(data);
        maze.setData(data);
    }

    private void randomizeData(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            int numOfPathsInRow = (int) (Math.random() * (data[i].length - 1));
            for (int j = 0; j < numOfPathsInRow; j++) {
                int pathInCol = (int) (Math.random() * (data[i].length - 1));
                data[i][pathInCol] = 0;
            }
        }
    }

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

    private void SetPositions(Maze maze, int numOfRows, int numOfCols) {
        do {
            maze.setStartPosition(createPosition(numOfRows, numOfCols));
        } while (null == maze.getStartPosition());
        do {
            maze.setGoalPosition(createPosition(numOfRows, numOfCols));
        } while (null == maze.getGoalPosition());
    }

//    private Position createPosition(int numOfRows, int numOfCols) {
//        int positionRowIndex = (int) (Math.random() * (numOfRows - 1));
//        int positionColIndex = (int) (Math.random() * (numOfCols - 1));
//        return new Position(positionRowIndex, positionColIndex);
//    }

}
