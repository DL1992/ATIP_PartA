package algorithms.search;

/**
 * Created by user on 12/04/2017.
 */
public abstract class AState {
    int cost;
    AState cameFrom;

    // TODO: write some code here!!

    public AState() {
        this.cameFrom = null;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public abstract boolean equals(AState other);

}
