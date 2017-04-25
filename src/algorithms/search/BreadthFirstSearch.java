package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Vlad on 4/15/2017.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable domain) {
        if (null != domain) {

            Solution ans;

            PriorityQueue<AState> openStateQueue = new PriorityQueue<>();
            ArrayList<AState> visitStateList = new ArrayList<>();

            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();

            startState.setCost(0);

            visitStateList.add(startState);
            openStateQueue.add(startState);

            while (!openStateQueue.isEmpty()) {
                this.evaluatedNodes++;
                AState currentState = openStateQueue.poll();

                if (currentState.equals(goalState)) {
                    ans = new Solution(createSolution(currentState));
                    return ans;
                }

                ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
                for (AState state :
                        successors) {
                    if (!visitStateList.contains(state)) {
                        state.setCameFrom(currentState);
                        visitStateList.add(state);
                        openStateQueue.add(state);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}
