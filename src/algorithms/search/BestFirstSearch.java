package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * This class use the Best First Search algorithm to solve a searching problem.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    /**
     * the Solution is created by using a normal BFS implementation.
     * using a PriorityQueue and a HashMap.
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (checkDomain(domain)) {
            Solution ans;
            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();
            PriorityQueue<AState> openPriorityQueue = new PriorityQueue<>();
            HashMap<String, AState> closedHashMap = new HashMap<>();
            openPriorityQueue.add(startState);
            while (!openPriorityQueue.isEmpty()) {
                this.evaluatedNodes++;
                AState currentState = openPriorityQueue.poll();
                closedHashMap.put(currentState.toString(), currentState);
                if (currentState.equals(goalState)) {
                    ans = new Solution(createSolution(currentState));
                    return ans;
                }
                ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
                for (AState state :
                        successors) {
                    if (!closedHashMap.containsKey(state.toString()) && !openPriorityQueue.contains(state)) {
                        state.setCameFrom(currentState);
                        openPriorityQueue.add(state);
                    } else if (currentState.equals(state.getCameFrom())) {
                        if (openPriorityQueue.contains(state)) {
                            openPriorityQueue.remove(state);
                        }
                        openPriorityQueue.add(state);
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
