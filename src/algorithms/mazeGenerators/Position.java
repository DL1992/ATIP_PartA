package algorithms.mazeGenerators;

/**
 * This class represent a Position.
 * a Position is a pair of row and col in a grid.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Position {
    private int rowIndex;
    private int columnIndex;

    /**
     * constructor for Position
     *
     * @param rowIndex    the rowIndex of the grid.
     * @param columnIndex the coIndex of the grid.
     */
    public Position(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    /**
     * @return the rowIndex of the Position
     */
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * @return the colIndex of the Position
     */
    public int getColumnIndex() {
        return this.columnIndex;
    }

    @Override
    /**
     * a Position string is in a format of {row,col}
     */
    public String toString() {
        return String.format("{%d,%d}", this.rowIndex, this.columnIndex);
    }

    /**
     * check if the position is the same as another Position.
     *
     * @param other is the Position to compare.
     * @return true if it's the same position, false otherwise.
     */
    public boolean equals(Position other) {
        return (other.rowIndex == this.rowIndex && other.columnIndex == this.columnIndex);
    }
}
