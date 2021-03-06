package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


/**
 * This class use the DFS algorithm to solve a searching problem.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    /**
     * the Solution is created by using a normal DFS implementation.
     * non-recursive. using a Stack.
     */
    @Override
    public Solution solve(ISearchable domain) {
        if (checkDomain(domain)) {
            Stack<AState> openStateStack = new Stack<>();
            HashMap<String, AState> visitStateHashMap = new HashMap<>();
            AState startState = domain.getStartState();
            AState goalState = domain.getGoalState();
            openStateStack.push(startState);
            while (!openStateStack.empty()) {
                AState currentState = openStateStack.pop();
                this.evaluatedNodes++;
                if (!visitStateHashMap.containsKey(currentState.toString())) {
                    visitStateHashMap.put(currentState.toString(), currentState);
                    ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
                    for (AState state :
                            successors) {
                        if (!visitStateHashMap.containsKey(state.toString())) {
                            state.setCameFrom(currentState);
                        }
                        openStateStack.push(state);
                    }
                }
            }


            return new Solution(createSolution(goalState));
        }
        return null;
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }
}
