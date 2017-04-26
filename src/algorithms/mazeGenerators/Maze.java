package algorithms.mazeGenerators;

/**
 * This class represent a Maze.
 * a maze is a grid of integer in which 1 is a wall and 0 is a path.
 * a Maze can only be created using a MazeGenerator.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Maze {

    /**
     * the starting Position of the maze. should never be null or outside the grid.
     */
    private Position startPosition;
    /**
     * the goal Position of the maze. should never be null, outside the grid nor equals to the starting position.
     */
    private Position goalPosition;
    /**
     * the grid og integers that are the maze. should not be null.
     */
    private int[][] data;

    /**
     * @return the start Position of the Maze.
     */
    public Position getStartPosition() {
        return this.startPosition;
    }

    /**
     * @return the goal Position of the Maze.
     */
    public Position getGoalPosition() {
        return this.goalPosition;
    }

    /**
     * @return the data of the Maze.
     */
    public int[][] getData() {
        return this.data;
    }

    /**
     * prints the Maze as a grid of 1's and 0's where 1 is a wall and 0 is path.
     * start position is indicated as S and goal Position as E
     * should look like
     * {0,0,1,0,S}
     * {1,1,1,E,0}
     * {1,1,0,0,1}
     */
    public void print() {
        for (int i = 0; i < this.data.length; i++) {
            System.out.print("{");
            for (int j = 0; j < this.data[i].length - 1; j++) {
                printCell(i, j, false);
            }
            printCell(i, this.data[i].length - 1, true);
        }
    }

    /**
     * a helper method for print, prints every cell in the grid according to its place and status.
     * start position is indicated as S and goal Position as E.
     *
     * @param rowIndex is the rowIndex in the grid we want to print.
     * @param colIndex is the colIndex in the grid we want to print.
     * @param flag     check if we are in the end of a row or not because the printing is different.
     */
    private void printCell(int rowIndex, int colIndex, boolean flag) {
        if (!flag) {
            if (isPos(rowIndex, colIndex, this.startPosition)) {
                System.out.print("S" + ",");
            } else if (isPos(rowIndex, colIndex, this.goalPosition)) {
                System.out.print("E" + ",");
            } else {
                System.out.print(this.data[rowIndex][colIndex] + ",");
            }
        } else {
            if (isPos(rowIndex, colIndex, this.startPosition)) {
                System.out.println("S" + "}");
            } else if (isPos(rowIndex, colIndex, this.goalPosition)) {
                System.out.println("E" + "}");
            } else {
                System.out.println(this.data[rowIndex][colIndex] + "}");
            }
        }
    }

    /**
     * set the start Position of the Maze.
     * the position must be inside the gird of the maze.
     *
     * @param startPosition is the Position we want to set as the start position.
     */
    protected void setStartPosition(Position startPosition) {
        this.startPosition = checkPosition(startPosition) ? startPosition : null;
    }

    /**
     * set the goal Position of the Maze.
     * goal position can't be the same as the start position and must be inside the gird of the maze.
     *
     * @param goalPosition is the Position we want to set as the goal position.
     */
    protected void setGoalPosition(Position goalPosition) {
        this.goalPosition = (checkPosition(goalPosition) && !goalPosition.equals(this.startPosition)) ? goalPosition : null;
    }

    /**
     * set tha grid of the maze.
     *
     * @param data id the new grid of the maze.
     */
    protected void setData(int[][] data) {
        this.data = data;
    }

    /**
     * check if a given position is legal for the Maze, legal means that it is not null and inside the grid.
     * helper function for the set's function.
     *
     * @param Position is the Position we want to check if its ok to put in the maze.
     * @return true if the position is not null and inside the gird of the maze, false otherwise.
     */
    public boolean checkPosition(Position Position) {
        if (Position != null) {
            if (this.data != null) {
                if (Position.getRowIndex() < this.data.length && Position.getRowIndex() >= 0) {
                    if (Position.getColumnIndex() >= 0 && Position.getColumnIndex() < this.data[Position.getRowIndex()].length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkIndexes(int i, int j) {
        if (this.data != null) {
            if (i < this.data.length && i >= 0) {
                if (j >= 0 && j < this.data[i].length) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if a specific coordinates equals a specific Position
     * helper function for print cell
     *
     * @param rowIndex is the rowIndex in the grid we want to compare.
     * @param colIndex is the colIndex in the grid we want to compare.
     * @param position is the position we want to compare the coordinates to.
     * @return true if the coordinates represents the Position' false otherwise.
     */
    private boolean isPos(int rowIndex, int colIndex, Position position) {
        return ((rowIndex == position.getRowIndex()) && (colIndex == position.getColumnIndex()));
    }
}


