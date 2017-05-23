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
public class BreadthFirstSearch extends ASearchingAlgorithm {

    /**
     * the Solution is created by using a normal BFS implementation.
     * using a Queue and a HashMap.
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (checkDomain(domain)) {
            Solution ans;
            PriorityQueue<AState> openStateQueue = new PriorityQueue<>();
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
