package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//TODO: javadoc of class here. trying to figure out a change for it to run faster!

/**
 * This class generate a maze using the prim algorithm.
 * it extends the abstract Class AMazeGenerator
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class MyMazeGenerator extends AMazeGenerator {
//    private Random randomGenerator;

    @Override
    /**
     * generta a random maze using the prim's algorithm.
     */
    public Maze generate(int numOfRows, int numOfCols) {
        if (numOfRows > 0 && numOfCols > 0) {
            if (!(numOfRows == 1 && numOfCols == 1)) {
//                this.randomGenerator = new Random();
                Maze maze = new Maze();
                int[][] data = new int[numOfRows][numOfCols];
                initData(data);
                maze.setData(data);
                usePrim(maze, numOfRows, numOfCols);
                return maze;
            }
        }
        return null;
    }

    /**
     * @param maze
     * @param numOfRows
     * @param numOfCols
     */
    private void usePrim(Maze maze, int numOfRows, int numOfCols) {

        HashSet<String> mazeHS = new HashSet<>();
//        HashMap<String, Position> mazeHM = new HashMap<>();
        //HashMap<String, Position> wallHM = new HashMap<>();
        ArrayList<Position> wallList = new ArrayList<>();
        ArrayList<Position> mazeList = new ArrayList<>();

        Position startPos = createPosition(numOfRows, numOfCols);
        maze.setStartPosition(startPos);

        setPartOfMaze(maze, mazeHS, mazeList, startPos);
        addWallsToList(maze, mazeHS, wallList, startPos);

        int index; // saves a index of the random pick wall
        while (!wallList.isEmpty()) {
            index = this.randomGenerator.nextInt(wallList.size());
            Position randomWall = wallList.get(index);
            if (countNeighbors(maze, randomWall) == 1) {
                setPartOfMaze(maze, mazeHS, mazeList, randomWall);
                addWallsToList(maze, mazeHS, wallList, randomWall);
            }
            wallList.remove(index);
        }

        Position endPos = pickEndPos(maze, mazeList);
        maze.setGoalPosition(endPos);
    }

    private void setPartOfMaze(Maze maze, HashSet<String> mazeHM, ArrayList<Position> mazeList, Position position) {
        int[][] data = maze.getData();
        data[position.getRowIndex()][position.getColumnIndex()] = 0;
        maze.setData(data);
        mazeHM.add(position.toString());
        mazeList.add(position);
    }

    private void addWallsToList(Maze maze, HashSet<String> mazeHM, ArrayList<Position> wallList, Position position) {
        if (maze.checkIndexes(position.getRowIndex() + 1, position.getColumnIndex()) && !mazeHM.contains( String.format("{%d,%d}", position.getRowIndex() + 1, position.getColumnIndex()) )) {
            wallList.add(Position.getPosition(position.getRowIndex() + 1, position.getColumnIndex()));
        }
        if (maze.checkIndexes(position.getRowIndex() - 1, position.getColumnIndex()) && !mazeHM.contains(String.format("{%d,%d}", position.getRowIndex() - 1, position.getColumnIndex()))) {
            wallList.add(Position.getPosition(position.getRowIndex() - 1, position.getColumnIndex()));
        }
        if (maze.checkIndexes(position.getRowIndex(), position.getColumnIndex() - 1) && !mazeHM.contains(String.format("{%d,%d}", position.getRowIndex(), position.getColumnIndex() - 1 ))) {
            wallList.add(Position.getPosition(position.getRowIndex(), position.getColumnIndex() - 1));
        }
        if (maze.checkIndexes(position.getRowIndex(), position.getColumnIndex() + 1) && !mazeHM.contains(String.format("{%d,%d}", position.getRowIndex(), position.getColumnIndex() + 1 ))) {
            wallList.add(Position.getPosition(position.getRowIndex(), position.getColumnIndex() + 1));
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

        if (maze.checkIndexes(position.getRowIndex() + 1, position.getColumnIndex())) {
            if (data[position.getRowIndex() + 1][position.getColumnIndex()] == 0) {
                neighborCounter++;
            }
        }
        if (maze.checkIndexes(position.getRowIndex() - 1, position.getColumnIndex())) {
            if (data[position.getRowIndex() - 1][position.getColumnIndex()] == 0) {
                neighborCounter++;
            }
        }
        if (maze.checkIndexes(position.getRowIndex(), position.getColumnIndex() - 1)) {
            if (data[position.getRowIndex()][position.getColumnIndex() - 1] == 0) {
                neighborCounter++;
            }
        }
        if (maze.checkIndexes(position.getRowIndex(), position.getColumnIndex() + 1)) {
            if (data[position.getRowIndex()][position.getColumnIndex() + 1] == 0) {
                neighborCounter++;
            }
        }
        return neighborCounter;
    }
}
