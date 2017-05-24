package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class represent a Position.
 * a Position is a pair of row and col in a grid.
 * it has a in-built objectPool to boost large creations
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Position implements Serializable {
    private int rowIndex;
    private int columnIndex;
    static HashMap<String, Position> posPool = new HashMap<>();


    /**
     * empty constructor for Position.
     */
    public Position(){}


    /**
     * this function is in charge of the position pool.
     *
     * @param rowIndex    the rowIndex of the grid.
     * @param columnIndex the coIndex of the grid.
     * @return The Poistion of the given indexes.
     */
    public static Position getPosition(int rowIndex, int columnIndex) {
        if (posPool.containsKey(String.format("{%d,%d}", rowIndex, columnIndex))) {
            return posPool.get(String.format("{%d,%d}", rowIndex, columnIndex));
        } else {
            return new Position(rowIndex, columnIndex);
        }
    }

    /**
     * constructor for Position
     *
     * @param rowIndex    the rowIndex of the grid.
     * @param columnIndex the coIndex of the grid.
     */
    private Position(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        posPool.put(this.toString(), this);
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

    /**
     * set the row index of the Position.
     *
     * @param rowIndex is the row index we want to set as the row index for this position.
     */
    public void setRowIndex( int rowIndex ) { this.rowIndex= rowIndex; }

    /**
     * set the column index of the Position.
     *
     * @param columnIndex is the column index we want to set as the column index for this position.
     */
    public void setColumnIndex( int columnIndex ) { this.columnIndex = columnIndex; }

    @Override
    /**
     * a Position string is in a format of {row,col}
     */
    public String toString() {
        return String.format("{%d,%d}", this.rowIndex, this.columnIndex);
    }

    @Override
    public boolean equals(Object obj) {
        return equals((Position) obj);
    }

    /**
     * check if the position is the same as another Position.
     *
     * @param other is the Position to compare.
     * @return true if it's the same position, false otherwise.
     */
    public boolean equals(Position other) {
        if (null != other) {
            return (other.rowIndex == this.rowIndex && other.columnIndex == this.columnIndex);
        }
        return false;
    }
}
