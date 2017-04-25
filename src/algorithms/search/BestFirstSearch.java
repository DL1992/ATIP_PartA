package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;
//TODO: write javadoc, change to hashMap

/**
 * Created by Vlad on 4/15/2017.
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable domain) {
        if (null != domain) {
            Solution ans;
            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();
            PriorityQueue<AState> open = new PriorityQueue<>();
            ArrayList<AState> closed = new ArrayList<>();
            open.add(startState);
            while (!open.isEmpty()) {
                this.evaluatedNodes++;
                AState currentState = open.poll();
                closed.add(currentState);
                if (currentState.equals(goalState)) {
                    ans = new Solution(createSolution(currentState));
                    return ans;
                }
                ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
                for (AState state :
                        successors) {
                    if (!closed.contains(state) && !open.contains(state)) {
                        state.setCameFrom(currentState);
                        open.add(state);
                    } else {
                        AState oldState = startState;
                        for (int i = 0; i < closed.size(); i++) {
                            if ((closed.get(i)).equals(state)) {
                                oldState = closed.get(i);
                            }
                        }
                        if (state.getCost() < oldState.getCost()) {
                            closed.remove(oldState);
                            if (open.contains(state)) {
                                open.remove(state);
                            }
                            open.add(state);
                        }
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
