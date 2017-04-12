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
}
