package algorithms.search;

/**
 * This class is the Interface for a searching algorithm.
 * the purpose of a searching algorithm is to search and find a solution to a problem.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public interface ISearchingAlgorithm {
    /**
     * This function should solve any problem that can be solve using a searching algorithm.
     * in the process the algorithm should count the number of nodes evaluated before returning the solution.
     *
     * @param domain the Searchable object which we want to solve a searching problem on.
     * @return a Solution of the given problem.
     */
    public Solution solve(ISearchable domain);

    /**
     * @return the name of the searching algorithm.
     */
    public String getName();

    /**
     * @return the number of nodes the algorithm evaluated before finding the solution.
     */
    public int getNumberOfNodesEvaluated();
}
