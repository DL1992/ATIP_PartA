package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;

import java.util.Arrays;


/**
 * Created by user on 11/04/2017.
 */
public class RunMazeGenerator {
    public static void main(String[] args) {
        Maze maza = new MyMazeGenerator().generate(50, 50);
        byte[] byti = maza.toByteArray();
        maza.print();
        System.out.println(maza.getStartPosition().toString());
        System.out.println(maza.getGoalPosition().toString());
        System.out.println(Arrays.toString(byti));
//        testMazeGenerator(new SimpleMazeGenerator());
//        testMazeGenerator(new MyMazeGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(2/*rows*/, 2/*columns*/)));
        // generate another maze
        Maze maze = mazeGenerator.generate(10/*rows*/, 10/*columns*/);
        // prints the maze
        maze.print();
//         get the maze entrance
        Position startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}
