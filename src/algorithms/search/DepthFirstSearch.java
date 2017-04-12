package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by user on 12/04/2017.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable domain) {
        Stack<AState> openStateStack = new Stack<>();
        ArrayList<AState> visitStateList = new ArrayList<>();
        AState startState = domain.getStartState();
        openStateStack.push(startState);
        while (!openStateStack.empty()) {
            AState currentState = openStateStack.pop();
            this.evaluatedNodes++;
            if (!visitStateList.contains(currentState)) {
                visitStateList.add(currentState);
                ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
                for (AState state :
                        successors) {
                    state.setCameFrom(currentState);
                    openStateStack.push(state);
                }
            }
        }
        Solution ans = new Solution(createSolution(visitStateList.get(visitStateList.indexOf(domain.getGoalState()))));
        return ans;
    }

    private ArrayList<AState> createSolution(AState goalState) {
        ArrayList<AState> solutionArrayList = new ArrayList<>();
        AState currentState = goalState;
        while (null != currentState) {
            solutionArrayList.add(currentState);
            currentState = currentState.getCameFrom();
        }
        Collections.reverse(solutionArrayList);
        return solutionArrayList;
    }


    @Override
    public String getName() {
        return "Depth First Search";
    }
}
