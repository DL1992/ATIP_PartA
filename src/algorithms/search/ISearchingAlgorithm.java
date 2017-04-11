package algorithms.search;

/**
 * Created by user on 11/04/2017.
 */
public interface ISearchingAlgorithm {
    public Solution solve(ISearchable domain);

    public String getName();

    public int getNumberOfNodesEvaluated();
}
