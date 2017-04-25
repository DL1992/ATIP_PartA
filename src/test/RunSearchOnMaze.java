package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

/**
 * Created by user on 11/04/2017.
 */
public class RunSearchOnMaze {
    public static void main(String[] args) {
        long lstartTime = System.currentTimeMillis();
//        IMazeGenerator mg = new SimpleMazeGenerator();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(3, 3);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);

        solveProblem(searchableMaze, new BreadthFirstSearch());
        solveProblem(searchableMaze, new DepthFirstSearch());
        solveProblem(searchableMaze, new BestFirstSearch());
        long end = System.currentTimeMillis();
        long ans = end - lstartTime;
        System.out.println(String.format("start time: %s. end time: %s. total:%s", lstartTime, end, ans));
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }
    }
}