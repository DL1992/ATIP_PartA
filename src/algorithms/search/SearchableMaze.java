package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * This class is an object adapter class.
 * serachble maze is a searching problem of the maze domain.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;

    /**
     * constructor for the SearchableMaze.
     *
     * @param maze the maze we want to solve a problem on.
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    /**
     * return a new MazeState with default params
     */
    public AState getStartState() {
        return new MazeState(this.maze.getStartPosition(), 0, null);
    }

    @Override
    /**
     * return a new MazeState with default params
     */
    public AState getGoalState() {
        return new MazeState(this.maze.getGoalPosition(), 0, null);
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if (null != state) {
            ArrayList<AState> ansArrayList = new ArrayList<>();
            MazeState mazeState = (MazeState) state;
            if (null != this.maze) {
                int[][] mazeData = this.maze.getData();
                addALLToAnsArrayList(ansArrayList, mazeData, mazeState, mazeState.getPosition().getRowIndex(), mazeState.getPosition().getColumnIndex());
                return ansArrayList;
            }
        }
        return null;
    }

    /**
     * Add all the possibleStates fron a given state to the list. in a maze you can only go up,down,left,right and all at the same cost.
     * helper function for getAllPossibleStates.
     *
     * @param ansArrayList the array we wish to return.
     * @param mazeData     the actual grid of the maze.
     * @param mazeState    the given state.
     * @param rowIndex     the row of the given state
     * @param columnIndex  the column of the given state
     */
    private void addALLToAnsArrayList(ArrayList<AState> ansArrayList, int[][] mazeData, MazeState mazeState, int rowIndex, int columnIndex) {
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex - 1, columnIndex);
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex, columnIndex + 1);
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex + 1, columnIndex);
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex, columnIndex - 1);
    }

    /**
     * add a single MazeState to the list according to the coordinates.
     * the cost of each step in a maze is 1. hard coded.
     *
     * @param ansArrayList the arrayList we wish to return
     * @param mazeData     the actual grid of the maze.
     * @param mazeState    the given state.
     * @param rowIndex     the row of the given state
     * @param colIndex     the column of the given state
     */
    private void addToAnsArrayList(ArrayList<AState> ansArrayList, int[][] mazeData, MazeState mazeState, int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < mazeData.length) {
            if (colIndex >= 0 && colIndex < mazeData[rowIndex].length) {
                if (mazeData[rowIndex][colIndex] == 0) {
                    MazeState mazeStateToArrayList = new MazeState(new Position(rowIndex, colIndex), mazeState.getCost() + 1, mazeState);
                    ansArrayList.add(mazeStateToArrayList);
                }
            }
        }
    }
}
