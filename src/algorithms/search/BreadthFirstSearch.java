package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
//TODO: write javadoc.

/**
 * Created by Vlad on 4/15/2017.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable domain) {
        if (null != domain) {

            Solution ans;

            PriorityQueue<AState> openStateQueue = new PriorityQueue<>();
//            ArrayList<AState> visitStateList = new ArrayList<>();
            HashMap<String, AState> visitStateHashMap = new HashMap<>();
            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();

            startState.setCost(0);

            visitStateHashMap.put(startState.toString(), startState);
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
                    if (!visitStateHashMap.containsKey(state.toString())) {
                        state.setCameFrom(currentState);
                        visitStateHashMap.put(state.toString(), state);
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
