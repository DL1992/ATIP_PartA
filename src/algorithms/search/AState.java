package algorithms.search;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class is abstract class which represent a state in searchable problem.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public abstract class AState implements Comparable<AState>,Serializable {
    protected String state;
    protected int cost;
    protected AState cameFrom;

    public AState(){}


    /**
     * constructor for the Astate.
     *
     * @param state    the state of the Astate. unique iD.
     * @param cost     the cost of getting to this state.
     * @param cameFrom the father state from which this state was discover.
     */
    public AState(String state, int cost, AState cameFrom) {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * set the cost of the Astate.
     *
     * @param cost the new cost of the Astate.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * set the father state of the Astate.
     *
     * @param cameFrom the new father State of the AState.
     */
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * @return the cost of the Astate.
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * @return the Astate from which this AState was discover.
     */
    public AState getCameFrom() {
        return this.cameFrom;
    }

    @Override
    public boolean equals(Object obj) {
        return equals((AState) obj);
    }

    /**
     * checks if a Astate is the same as this.
     * the difference is in the state.
     *
     * @param other the other AState we want to check.
     * @return true if it is the same AState, false otherwise.
     */
    public abstract boolean equals(AState other);

    @Override
    public int hashCode() {
        return this.state.hashCode();
    }

    @Override
    public String toString() {
        return this.state;
    }

    @Override
    public int compareTo(AState other) {
        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than
        // other and 0 if they are supposed to be equal
        if (this.getCost() < other.getCost()) {
            return -1;
        }
        if (this.getCost() > other.getCost()) {
            return 1;
        }
        return 0;
    }

}
