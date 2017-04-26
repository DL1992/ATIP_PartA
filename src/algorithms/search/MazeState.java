package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.HashMap;

/**
 * This class  represent a state in a Maze domain.
 * a maze state is a state in maze domain which has a position.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class MazeState extends AState {
    static HashMap<String, MazeState> mazeStatePool = new HashMap<>();
    private Position position;

    /**
     * constructor for the MazeState.
     * the state of MazeState is the string of it's position.
     *
     * @param position the position of the state in maze. should be unique.
     * @param cost     the cost of getting to this state.
     * @param cameFrom the father state from which this state was discover.
     */
    private MazeState(Position position, int cost, AState cameFrom) {
        super(position.toString(), cost, cameFrom);
        this.position = position;
    }

    /**
     * @return the position of the maze State
     */
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(AState other) {
        if (other != null) {
            MazeState compare = (MazeState) other;
            return equals(compare);
        }
        return false;
    }

    @Override
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * checks if this mazeState is the Same to another.
     * the test is using the position of each mazeState.
     *
     * @param other the  MazeState we want to check
     * @return true if the two states are the same, false otherwise.
     */
    private boolean equals(MazeState other) {
        return this.position.equals(other.position);
    }

    /**
     * In charge of the mazeState pool. if doesnt exist creates a new state.
     *
     * @param state    the key in the pool
     * @param rowIndex the row index
     * @param colIndex the col index
     * @param cost     the cost to get to that state
     * @param cameFrom the father of thata state
     * @return the state with the correct key, or a new state.
     */
    static MazeState getMazeStateFromPool(String state, int rowIndex, int colIndex, int cost, AState cameFrom) {
        if (mazeStatePool.containsKey(state)) {
            return mazeStatePool.get(state);
        }
        MazeState newMazeState = new MazeState(Position.getPosition(rowIndex, colIndex), cost, cameFrom);
        mazeStatePool.put(state, newMazeState);
        return newMazeState;
    }

}
