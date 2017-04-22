package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 12/04/2017.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int evaluatedNodes;

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
