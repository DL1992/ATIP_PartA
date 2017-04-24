package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * Created by user on 12/04/2017.
 */
public class MazeState extends AState {
    private Position position;

    public MazeState(Position position, int cost, AState cameFrom) {
        super(position.toString(), cost, cameFrom);
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(AState other) {
        if (other != null) {
            MazeState compare = (MazeState) other;
            return equals(compare);
        }
        return false;
    }

    @Override
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    private boolean equals(MazeState other) {
        return this.position.equals(other.position);
    }

    //TODO: check if need to print other fields
    @Override
    public String toString() {
        return this.position.toString();
    }

}
