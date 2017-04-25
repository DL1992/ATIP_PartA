package algorithms.search;

import java.util.ArrayList;

/**
 * This class is the Interface for a Searchable problem.
 * the purpose of a Searchable problem is that it can be solve by the use of searching algorithms.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface ISearchable {

    /**
     * every problem should decide its start state. from which every searching will be able to start.
     *
     * @return the Start State of the problem.
     */
    AState getStartState();

    /**
     * every problem should decide its goal state, which is the place we want to get to in the end.
     *
     * @return the goal State of the problem.
     */
    AState getGoalState();

    /**
     * in order to work a problem should be able to tell from any given state what are to possibale state to get to from it.
     * for the use of the searching algorithms.
     * this function should decide the different state for each problem. decide the cost if there is one and so on.
     *
     * @param state the state we are in and from which we want to see where can we go.
     * @return all the possible states that we can get to from the current state in the domain.
     */
    ArrayList<AState> getAllPossibleStates(AState state);
}
