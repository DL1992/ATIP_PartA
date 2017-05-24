package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * This class represent a Solution to a searching problem.
 * a Solution is the path of state from start to goal in the problem.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Solution implements Serializable {
    ArrayList<AState> solutionPath;

    /**
     * empty constructor for Solution.
     */
    public Solution() {
    }

    /**
     * constructor for the solution.
     *
     * @param solutionPath the array list which represent a correct path from start state to goal state in a given problem.
     */
    public Solution(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }

    /**
     * @return the solution path.
     */
    public ArrayList<AState> getSolutionPath() {
        return this.solutionPath;
    }


    /**
     * set's the solution path to the given arrayList.
     *
     * @param solutionPath the ArrayList of the solution path.
     */
    public void setSolutionPath(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }

    @Override
    public String toString() {
        return "";
    }
}
