package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * Created by user on 12/04/2017.
 */
public class MazeState extends AState {
    private Position position;

    public MazeState(Position position) {
        super(position.toString());
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(AState other) {
        MazeState compare = (MazeState) other;
        return equals(compare);
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
