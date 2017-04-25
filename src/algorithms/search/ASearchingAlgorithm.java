package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is abstract class of a Searching algorithms.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int evaluatedNodes;

    /**
     * default constructor.
     * init evaluatedNodes to 0;
     */
    public ASearchingAlgorithm() {
        this.evaluatedNodes = 0;
    }

    @Override
    public abstract Solution solve(ISearchable domain);

    @Override
    public abstract String getName();

    @Override
    public int getNumberOfNodesEvaluated() {
        return this.evaluatedNodes;
    }

    /**
     * a helper function for every searching algorithms which need to return a solution as an array list.
     * backtracking from the goal position to the start position and reverse the order for a proper path.
     *
     * @param goalState the goal state of the searchable domain
     * @return the path of the problem from start to finish.
     */
    protected ArrayList<AState> createSolution(AState goalState) {
        ArrayList<AState> solutionArrayList = new ArrayList<>();
        AState currentState = goalState;
        while (null != currentState) {
            solutionArrayList.add(currentState);
            currentState = currentState.getCameFrom();
        }
        Collections.reverse(solutionArrayList);
        return solutionArrayList;
    }
}
