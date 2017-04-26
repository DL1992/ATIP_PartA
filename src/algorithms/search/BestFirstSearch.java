package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
//TODO: write javadoc, change to hashMap

/**
 * Created by Vlad on 4/15/2017.
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable domain) {
        if (checkDomain(domain)) {
            Solution ans;
            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();
            PriorityQueue<AState> open = new PriorityQueue<>();
            HashMap<String, AState> closed = new HashMap<>();
            open.add(startState);
            while (!open.isEmpty()) {
                this.evaluatedNodes++;
                AState currentState = open.poll();
                closed.put(currentState.toString(), currentState);
                if (currentState.equals(goalState)) {
                    ans = new Solution(createSolution(currentState));
                    return ans;
                }
                ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
                for (AState state :
                        successors) {
                    if (!closed.containsKey(state.toString()) && !open.contains(state)) {
                        state.setCameFrom(currentState);
                        open.add(state);
                    } else if (currentState.equals(state.getCameFrom())) {
                        if (open.contains(state)) {
                            open.remove(state);
                        }
                        open.add(state);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "Best First Search";
    }

}
