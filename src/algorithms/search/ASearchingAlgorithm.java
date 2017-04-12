package algorithms.search;

/**
 * Created by user on 12/04/2017.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private int evaluatedNodes;

    public ASearchingAlgorithm() {
        this.evaluatedNodes = 0;
    }

    @Override
    public abstract Solution solve(ISearchable domain);

    @Override

    public abstract String getName();

    @Override
    public int getNumberOfNodesEvaluated() {
        return this.evaluatedNodes;
    }
}
