package algorithms.mazeGenerators;

import java.util.ArrayList;
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
    /**
     * generates a random maze using the prim's algorithm.
     * this is done by starting with a grid full of walls, randomly picking a start position,
     * and randomly dig out the maze from the already existing one, while avoiding circles
     */
    @Override
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
     * this function executes the prim algorithm for maze generation.
     * helper function to generate - does the actual generation using the prim algorithm
     *
     * @param maze      the Maze we set its start and goal positions.
     * @param numOfRows the index that contains the number of rows in the maze.
     * @param numOfCols the index that contains the number of cols in the maze.
     */
    private void usePrim(Maze maze, int numOfRows, int numOfCols) {

        HashSet<String> mazeHS = new HashSet<>();
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

    /**
     * this function adds a given positions as part of the maze.
     * helper function for usePrim - used to add a cell as a part of the maze.
     *
     * @param maze     the Maze we set its start and goal positions.
     * @param mazeHM   an HashMap that contains all the positions that are part of the maze.
     * @param mazeList an arrayList that contains all the positions that are part of the maze.
     * @param position the position in the maze which we wish to add the neighbors of.
     */
    private void setPartOfMaze(Maze maze, HashSet<String> mazeHM, ArrayList<Position> mazeList, Position position) {
        int[][] data = maze.getData();
        data[position.getRowIndex()][position.getColumnIndex()] = 0;
        maze.setData(data);
        mazeHM.add(position.toString());
        mazeList.add(position);
    }

    /**
     * this function creates and adds the positions of neighbors of a cell that specified in position.
     * helper function for usePrim - used to add a cells neighbors to the wallList.
     *
     * @param maze     the Maze we set its start and goal positions.
     * @param mazeHM   an HashMap that contains all the positions that are part of the maze.
     * @param wallList an arrayList that contains all the positions that are neighboring walls in the maze.
     * @param position the position in the maze which we wish to add the neighbors of.
     */
    private void addWallsToList(Maze maze, HashSet<String> mazeHM, ArrayList<Position> wallList, Position position) {
        if (maze.checkIndexes(position.getRowIndex() + 1, position.getColumnIndex()) && !mazeHM.contains(String.format("{%d,%d}", position.getRowIndex() + 1, position.getColumnIndex()))) {
            wallList.add(Position.getPosition(position.getRowIndex() + 1, position.getColumnIndex()));
        }
        if (maze.checkIndexes(position.getRowIndex() - 1, position.getColumnIndex()) && !mazeHM.contains(String.format("{%d,%d}", position.getRowIndex() - 1, position.getColumnIndex()))) {
            wallList.add(Position.getPosition(position.getRowIndex() - 1, position.getColumnIndex()));
        }
        if (maze.checkIndexes(position.getRowIndex(), position.getColumnIndex() - 1) && !mazeHM.contains(String.format("{%d,%d}", position.getRowIndex(), position.getColumnIndex() - 1))) {
            wallList.add(Position.getPosition(position.getRowIndex(), position.getColumnIndex() - 1));
        }
        if (maze.checkIndexes(position.getRowIndex(), position.getColumnIndex() + 1) && !mazeHM.contains(String.format("{%d,%d}", position.getRowIndex(), position.getColumnIndex() + 1))) {
            wallList.add(Position.getPosition(position.getRowIndex(), position.getColumnIndex() + 1));
        }
    }

    /**
     * this function randomly creates and sets the goal position of the maze.
     * helper function for usePrim - used to randomly pick and set the goal position.
     *
     * @param maze     the Maze we set its start and goal positions.
     * @param mazeList an arrayList that contains all the positions that are part of the maze.
     */
    private Position pickEndPos(Maze maze, ArrayList<Position> mazeList) {
        Position startPosition = maze.getStartPosition();
        int index = this.randomGenerator.nextInt(mazeList.size());
        Position goalPosition = mazeList.get(index);
        while (startPosition.equals(goalPosition)) {
            index = this.randomGenerator.nextInt(mazeList.size());
            goalPosition = mazeList.get(index);
        }
        return goalPosition;
    }

    /**
     * this function counts the amount of neighboring cells that are part of the maze.
     * helper function for usePrim - used to check if removing a wall will create a circle in the maze.
     *
     * @param maze     the Maze we set its start and goal positions.
     * @param position the position in the maze which we wish to count the neighbors of.
     */
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
