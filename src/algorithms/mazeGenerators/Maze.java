package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent a Maze.
 * a maze is a grid of integer in which 1 is a wall and 0 is a path.
 * a Maze can only be created using a MazeGenerator.
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class Maze implements Serializable {

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
     * constructor for Maze.
     * decompresses a byte array into a maze using our secret decompression method.
     *
     * @param ByteArray is the byte array we decompress information from.
     */
    public Maze(byte[] ByteArray) {
        setPositionsFromByteArray(ByteArray);
        setDataFromByteArray(ByteArray);
    }

    /**
     * empty constructor for Maze.
     */
    public Maze() {
    }

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
    public void setStartPosition(Position startPosition) {
        this.startPosition = checkPosition(startPosition) ? startPosition : null;
    }

    /**
     * set the goal Position of the Maze.
     * goal position can't be the same as the start position and must be inside the gird of the maze.
     *
     * @param goalPosition is the Position we want to set as the goal position.
     */
    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = (checkPosition(goalPosition) && !goalPosition.equals(this.startPosition)) ? goalPosition : null;
    }

    /**
     * set tha grid of the maze.
     *
     * @param data id the new grid of the maze.
     */
    public void setData(int[][] data) {
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

    /**
     * check if the given indexes are legal for the Maze, legal means that it is inside the grid.
     *
     * @param row is the row we want to check for the maze.
     * @param col is the col we want to check for the maze.
     * @return true if the indexes are inside the gird of the maze, false otherwise.
     */
    public boolean checkIndexes(int row, int col) {
        if (this.data != null) {
            if (row < this.data.length && row >= 0) {
                if (col >= 0 && col < this.data[row].length) {
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


    /**
     * return the serializable form of the maze (as a byte array)
     * the first 30 slots would be in order (5 slot for each) : the row of the starting point, the col of the starting position
     * the row of the goal position, the col of the goal position, the number of rows in the maze, the number of cols in the maze
     * then we keep the data of the maze like this: each slot will keep how many consecutive 0 or 1 are there, starting with 0.
     *
     * @return byte form of the maze.
     */
    public byte[] toByteArray() {
        List<Byte> byteList = new LinkedList<>();
        addPositionsToList(byteList);
        addDataToList(byteList);
        return listToByteArray(byteList);

    }

    /**
     * add the size of the maze to list in its byte form.
     * helper function for toByteArray.
     *
     * @param byteList is the list that represents the byteArray.
     */
    private void addDataToList(List<Byte> byteList) {
        intToList(this.data.length, byteList); //add the number of rows to the list
        intToList(this.data[0].length, byteList);// add the number of cols to the list
        dataToList(byteList);
    }

    /**
     * transform the data of the maze to its byte form and add it to the list.
     * for simplicity first transform the 2d array into a 1d array.
     * helper fucntion for addDataToList.
     *
     * @param byteList is the list that represents the byteArray.
     */
    private void dataToList(List<Byte> byteList) {
        int[] tempArray = createArray(this.data);
        byte oneCount = 0;
        byte zeroCount = 0;
        int i = 0;
        while (i < tempArray.length) {
            while (i < tempArray.length && tempArray[i] == 0 && zeroCount < 127) {
                zeroCount++;
                i++;
            }
            byteList.add(zeroCount);
            zeroCount = 0;
            while (i < tempArray.length && tempArray[i] == 1 && oneCount < 127) {
                oneCount++;
                i++;
            }
            byteList.add(oneCount);
            oneCount = 0;
        }

    }

    /**
     * transform a 2d array to a 1d array.
     *
     * @param data the 2d array.
     * @return 1d array representation of the 2d array.
     */
    private int[] createArray(int[][] data) {
        int[] ans = new int[data.length * data[0].length];
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                ans[index] = data[i][j];
                index++;
            }
        }
        return ans;
    }

    /**
     * adds the start position and the goal position in their byte form(5 byte for each)
     * to the list that will turn into the byteArray.
     * helper function for toByteArray.
     *
     * @param byteList is the list that represents the byteArray.
     */
    private void addPositionsToList(List<Byte> byteList) {
        addPositionToList(this.startPosition, byteList);
        addPositionToList(this.goalPosition, byteList);
    }

    /**
     * adds a specific position to the byteList.
     * helper function for addPositionsToList.
     *
     * @param position the position we want to add to the list.
     * @param byteList the list that represents the byteArray.
     */
    private void addPositionToList(Position position, List<Byte> byteList) {
        intToList(position.getRowIndex(), byteList);
        intToList(position.getColumnIndex(), byteList);
    }

    /**
     * transform a int to a specific byte form.
     * we seperate the int into 5 groups.
     * for example number 202 would become 2,2,0,0,0.
     * helper function for addPositionToList and addDataToList.
     *
     * @param numToByte the int we want to translate to our byte form.
     * @param byteList  the list that represents the byteArray.
     */
    private void intToList(int numToByte, List<Byte> byteList) {
        for (int i = 0; i < 5; i++) {
            byteList.add((byte) (numToByte % 100));
            numToByte = numToByte / 100;
        }
    }

    /**
     * transform a list of byte into a byte array.
     * helper function for toByteArray.
     *
     * @param byteList the list that we transform into array
     * @return the array presentation of the list.
     */
    private byte[] listToByteArray(List<Byte> byteList) {
        byte[] returnArray = new byte[byteList.size()];
        int i = 0;
        for (Byte b : byteList) {
            returnArray[i++] = b.byteValue();
        }
        return returnArray;
    }

    /**
     * sets the start and goal positions of this maze as
     * specified in the byte array given (using our secret decompression method).
     *
     * @param byteArray the byte array that we decompress for the required information.
     */
    private void setPositionsFromByteArray(byte[] byteArray) {
        this.startPosition = Position.getPosition(getIntFromByteArray(0, byteArray), getIntFromByteArray(5, byteArray));
        this.goalPosition = Position.getPosition(getIntFromByteArray(10, byteArray), getIntFromByteArray(15, byteArray));
    }

    /**
     * sets the data of this maze as specified in the byte array given.
     * (using our secret decompression method).
     *
     * @param byteArray the byte array that we decompress for the required information.
     */
    private void setDataFromByteArray(byte[] byteArray) {
        int rowIndex = getIntFromByteArray(20, byteArray);
        int colIndex = getIntFromByteArray(25, byteArray);
        int dataLength = rowIndex * colIndex;
        int[] data1D = new int[dataLength];
        this.data = new int[rowIndex][colIndex];
        setData1dFromByteArray(data1D, byteArray);
        setDataFrom1D(data1D);
    }

    /**
     * sets the data1D array with the decompressed information from the byte array given.
     * (using our secret decompression method).
     *
     * @param data1D    is the array we save the for decompressed information in.
     * @param byteArray is the byte array that we decompress for the required information.
     */
    private void setData1dFromByteArray(int[] data1D, byte[] byteArray) {
        int arrayIndex = 0;
        for (int i = 30; i < byteArray.length; i++) {
            for (int j = 0; j < byteArray[i]; j++) {
                if (i % 2 == 0) {
                    data1D[arrayIndex] = 0;
                } else {
                    data1D[arrayIndex] = 1;
                }
                arrayIndex++;
            }
        }
    }

    /**
     * sets the data of this maze as the the data from the 1D array given.
     *
     * @param data1D is the array that we get the required information from.
     */
    private void setDataFrom1D(int[] data1D) {
        int arrayIndex = 0;
        for (int i = 0; i < this.data[0].length; i++) {
            for (int j = 0; j < this.data.length; j++) {
                this.data[i][j] = data1D[arrayIndex];
                arrayIndex++;
            }
        }
    }

    /**
     * gets an integer from the compressed byte array
     * (using our secret decompression method).
     *
     * @param startLocation is the start location in the byte array of the required int.
     * @param byteArray     is the byte array we  decompressed information from.
     * @return the required int.
     */
    private int getIntFromByteArray(int startLocation, byte[] byteArray) {
        int theInt = 0;
        for (int i = 0; i < 5; i++) {
            theInt = theInt + (int) (byteArray[startLocation + i] * Math.pow(100, i));
        }
        return theInt;
    }
}


