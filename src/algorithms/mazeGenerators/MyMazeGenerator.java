package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
//TODO: javadoc of class here.

/**
 * This class generate a maze using the prim algorithm.
 * it extends the abstract Class AMazeGenerator
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class MyMazeGenerator extends AMazeGenerator {
    private Random randomGenerator;

    @Override
    /**
     * genertae a random maze using the prim's algorithm.
     */
    public Maze generate(int numOfRows, int numOfCols) {
        this.randomGenerator = new Random();
        Maze maze = new Maze();
        int[][] data = new int[numOfRows][numOfCols];
        initData(data);
        maze.setData(data);
        usePrim(maze, numOfRows, numOfCols);
        return maze;
    }

    private void usePrim(Maze maze, int numOfRows, int numOfCols) {
        ArrayList<Position> wallList = new ArrayList<>();
        ArrayList<Position> mazeList = new ArrayList<>();
        Position startPos = createPosition(numOfRows, numOfCols);
        mazeList.add(startPos);
        maze.setStartPosition(startPos);

        setPartOfMaze(mazeList, maze, startPos);
        addWallsToList(maze, wallList, startPos);

        int index;
        while (!wallList.isEmpty()) {
            index = this.randomGenerator.nextInt(wallList.size());
            Position randomWall = wallList.get(index);
            if (countNeighbors(maze, randomWall) == 1) {
                setPartOfMaze(mazeList, maze, randomWall);
                addWallsToList(maze, wallList, randomWall);
            }
            wallList.remove(index);
        }

        Position endPos = pickEndPos(maze, mazeList);
        maze.setGoalPosition(endPos);
    }

    private void setPartOfMaze(ArrayList<Position> mazeList, Maze maze, Position position) {
        int[][] data = maze.getData();
        data[position.getRowIndex()][position.getColumnIndex()] = 0;
        maze.setData(data);
        mazeList.add(position);
    }

    private void addWallsToList(Maze maze, ArrayList<Position> wallList, Position position) {
        Position up = new Position(position.getRowIndex() + 1, position.getColumnIndex());
        Position down = new Position(position.getRowIndex() - 1, position.getColumnIndex());
        Position left = new Position(position.getRowIndex(), position.getColumnIndex() - 1);
        Position right = new Position(position.getRowIndex(), position.getColumnIndex() + 1);
        if (maze.checkPosition(up)) {
            wallList.add(up);
        }
        if (maze.checkPosition(down)) {
            wallList.add(down);
        }
        if (maze.checkPosition(left)) {
            wallList.add(left);
        }
        if (maze.checkPosition(right)) {
            wallList.add(right);
        }

    }

    private Position pickEndPos(Maze maze, ArrayList<Position> mazeList) {
        Position startPosition = maze.getStartPosition();
        int index = this.randomGenerator.nextInt(mazeList.size());
        Position endPosition = mazeList.get(index);
        while (startPosition.equals(endPosition)) {
            index = this.randomGenerator.nextInt(mazeList.size());
            endPosition = mazeList.get(index);
        }
        return endPosition;
    }

    private int countNeighbors(Maze maze, Position position) {
        int neighborCounter = 0;
        int[][] data = maze.getData();

        Position up = new Position(position.getRowIndex() + 1, position.getColumnIndex());
        Position down = new Position(position.getRowIndex() - 1, position.getColumnIndex());
        Position left = new Position(position.getRowIndex(), position.getColumnIndex() - 1);
        Position right = new Position(position.getRowIndex(), position.getColumnIndex() + 1);

        if (maze.checkPosition(up)) {
            if (data[up.getRowIndex()][up.getColumnIndex()] == 0) {
                neighborCounter++;
            }
        }
        if (maze.checkPosition(down)) {
            if (data[down.getRowIndex()][down.getColumnIndex()] == 0) {
                neighborCounter++;
            }
        }
        if (maze.checkPosition(left)) {
            if (data[left.getRowIndex()][left.getColumnIndex()] == 0) {
                neighborCounter++;
            }
        }
        if (maze.checkPosition(right)) {
            if (data[right.getRowIndex()][right.getColumnIndex()] == 0) {
                neighborCounter++;
            }
        }
        return neighborCounter;
    }
}
