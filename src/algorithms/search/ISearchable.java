package algorithms.search;

import java.util.ArrayList;

/**
 * Created by user on 11/04/2017.
 */
public interface ISearchable {
    AState getStartState();

    AState getGoalState();

    ArrayList<AState> getAllPossibleStates(AState state);
}
