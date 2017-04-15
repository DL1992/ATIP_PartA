package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.*;

/**
 * Created by Vlad on 4/15/2017.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    @Override
    public Solution solve(ISearchable domain) {
        Solution ans;

        AState startState = domain.getStartState();
        AState goalState = domain.getGoalState();

        PriorityQueue<AState> queue = new PriorityQueue<>();
        ArrayList<AState> stateList = new ArrayList<>();

        stateList.add(startState);
        queue.add(startState);
        while(!queue.isEmpty()){
            this.evaluatedNodes++;
            AState currentState = queue.remove();
            if( currentState.equals(goalState) ){
                ans = new Solution(createSolution(currentState));
                return ans;
            }
            ArrayList<AState> successors = domain.getAllPossibleStates(currentState);
            for (AState state :
                    successors) {
                if(!stateList.contains(state)) {
                    state.setCameFrom(currentState);
                    stateList.add(state);
                    queue.add(state);
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
        Collections.reverse(solutionArrayList);;
        return solutionArrayList;
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }
}
