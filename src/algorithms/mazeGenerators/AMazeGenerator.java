package algorithms.mazeGenerators;

/**
 * Created by user on 11/04/2017.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int numOfRows, int numOfCols);

    @Override
    public long measureAlgorithmTimeMillis(int numOfRows, int numOfCols) {
        long lstartTime = System.currentTimeMillis();
        generate(numOfRows, numOfCols);
        long lendTime = System.currentTimeMillis();
        return lendTime - lstartTime;
    }

    protected void initData(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = 1;
            }

        }
    }
//
    protected Position createPosition(int numOfRows, int numOfCols) {
        int positionRowIndex = (int) (Math.random() * (numOfRows - 1));
        int positionColIndex = (int) (Math.random() * (numOfCols - 1));
        return new Position(positionRowIndex, positionColIndex);
    }
}
