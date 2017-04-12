package algorithms.mazeGenerators;

/**
 * Created by user on 11/04/2017.
 */
public class Maze {
    private Position startPosition;
    private Position goalPosition;
    private int[][] data;

    public Position getStartPosition() {
        return this.startPosition;
    }

    public Position getGoalPosition() {
        return this.goalPosition;
    }

    public int[][] getData() {
        return this.data;
    }

    public void print() {

        for (int i = 0; i < this.data.length; i++) {
            System.out.print("{");
            for (int j = 0; j < this.data[i].length - 1; j++) {
                printCell(i, j, false);
            }
            printCell(i, this.data[i].length - 1, true);
        }
    }

    private void printCell(int i, int j, boolean flag) {

        if (!flag) {
            if (isPos(i, j, this.startPosition)) {
                System.out.print("S" + ",");
            } else if (isPos(i, j, this.goalPosition)) {
                System.out.print("E" + ",");
            } else {
                System.out.print(this.data[i][j] + ",");
            }
        } else {
            if (isPos(i, j, this.startPosition)) {
                System.out.println("S" + "},");
            } else if (isPos(i, j, this.goalPosition)) {
                System.out.println("E" + "},");
            } else {
                System.out.println(this.data[i][j] + "}");
            }
        }
    }

    protected void setStartPosition(Position startPosition) {
        this.startPosition = checkPosition(startPosition) ? startPosition : null;
    }

    protected void setGoalPosition(Position goalPosition) {
        this.goalPosition = (checkPosition(goalPosition) && !goalPosition.equals(this.startPosition)) ? goalPosition : null;
    }

    protected void setData(int[][] data) {
        this.data = data;
    }

    private boolean checkPosition(Position Position) {
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

    private boolean isPos(int rowIndex, int colIndex, Position position) {
        return ((rowIndex == position.getRowIndex()) && (colIndex == position.getColumnIndex()));
    }
}


