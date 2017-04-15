package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by Vlad on 4/15/2017.
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable domain) {
        Solution ans;

        AState startState = domain.getStartState();
        AState goalState = domain.getGoalState();
        startState.setCost(0);
        PriorityQueue<AState> open = new PriorityQueue<>();
        ArrayList<AState> closed = new ArrayList<>();

        open.add(startState);
        while(!open.isEmpty()){
            this.evaluatedNodes++;
            AState currentState = open.remove();
            closed.add(currentState);
            if( currentState.equals(goalState) ){
                ans = new Solution(createSolution(currentState));
                return ans;
            }
            ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
            for (AState state :
                    successors) {
                if(!closed.contains(state) && !open.contains(state)) {
                    state.setCameFrom(currentState);
                    open.add(state);
                }
                else if(state.getCost() < currentState.getCost() ){
                    if(!open.contains(state)){
                        open.remove(state);
                    }
                    open.add(state);
                }
            }
        }
        return new Solution(createSolution(goalState));
    }

    private ArrayList<AState> createSolution(AState goalState) {
        ArrayList<AState> solutionArrayList = new ArrayList<>();
        AState currentState = goalState;
        while (null != currentState) {
            solutionArrayList.add(currentState);
            currentState = currentState.getCameFrom();
        }
        Collections.reverse(solutionArrayList);;;
        return solutionArrayList;
    }

    @Override
    public String getName() {
        return "Best First Search";
    }

}
