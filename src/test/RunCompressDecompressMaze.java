package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Aviadjo on 3/26/2017.
 */
public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        int counter = 0;
        for (int k = 102; k < 105; k++) {
            String mazeFileName = "savedMaze.maze";
            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(k, k); //Generate new maze

            try {
                // save maze to a file
                OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
                out.write(maze.toByteArray());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte savedMazeBytes[] = new byte[0];
            try {
                //read maze from file
                InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
                savedMazeBytes = new byte[maze.toByteArray().length];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Maze loadedMaze = new Maze(savedMazeBytes);
//            System.out.println();
//            maze.print();
//            System.out.println();
//            loadedMaze.print();
            boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
            if (areMazesEquals)
                counter++;
            System.out.println(String.format("Mazes equal: %s number is %d", areMazesEquals, k)); //maze should be equal to loadedMaze
        }
        System.out.println(counter);
    }
}