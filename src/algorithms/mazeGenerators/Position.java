package algorithms.mazeGenerators;

/**
 * Created by user on 11/04/2017.
 */
public class Position {
    private int rowIndex;
    private int columnIndex;

    public Position(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColumnIndex() {
        return this.columnIndex;
    }

    @Override
    public String toString() {
        return String.format("{%d,%d}", this.rowIndex, this.columnIndex);
    }

    public boolean equals(Position other) {
        return (other.rowIndex == this.rowIndex && other.columnIndex == this.columnIndex);
    }
}
