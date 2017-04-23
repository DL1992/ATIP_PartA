package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * Created by user on 12/04/2017.
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        return new MazeState(this.maze.getStartPosition(), 0, null);
    }

    @Override
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

    private void addALLToAnsArrayList(ArrayList<AState> ansArrayList, int[][] mazeData, MazeState mazeState, int rowIndex, int columnIndex) {
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex - 1, columnIndex);
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex, columnIndex + 1);
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex + 1, columnIndex);
        addToAnsArrayList(ansArrayList, mazeData, mazeState, rowIndex, columnIndex - 1);
    }

    private void addToAnsArrayList(ArrayList<AState> ansArrayList, int[][] mazeData, MazeState mazeState, int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < mazeData.length) {
            if (colIndex >= 0 && colIndex < mazeData[rowIndex].length) {
                if (mazeData[rowIndex][colIndex] == 0) {
                    MazeState mazeStateToArrayList = new MazeState(new Position(rowIndex, colIndex), mazeState.getCost() + 1, mazeState);
//                    mazeStateToArrayList.setCost(mazeState.getCost() + 1);
                    ansArrayList.add(mazeStateToArrayList);
                }
            }
        }
    }
}
