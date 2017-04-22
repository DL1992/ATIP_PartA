package algorithms.search;

/**
 * Created by user on 12/04/2017.
 */
public abstract class AState implements Comparable<AState> {
    protected String state;
    protected int cost;
    protected AState cameFrom;

    public AState(String state) {
        this.state = state;
        this.cost = 0;
        this.cameFrom = null;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public int getCost() {
        return this.cost;
    }

    public AState getCameFrom() {
        return this.cameFrom;
    }

    @Override
    public boolean equals(Object obj) {
        return equals((AState) obj);
    }

    public abstract boolean equals(AState other);
    
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
