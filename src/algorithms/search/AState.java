package algorithms.search;

/**
 * Created by user on 12/04/2017.
 */
public abstract class AState implements Comparable<AState>{
    int cost;
    AState cameFrom;

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

    @Override
    public int compareTo(AState other){
        // compareTo should return < 0 if this is supposed to be
        // less than other, > 0 if this is supposed to be greater than
        // other and 0 if they are supposed to be equal
        if( this.getCost() < other.getCost() )  {
            return -1;
        }
        if( this.getCost() > other.getCost() )  {
            return 1;
        }
        return 0;
    }

    public abstract boolean equals(AState other);
}
