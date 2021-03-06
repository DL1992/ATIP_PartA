package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

/**
 * Created by user on 11/04/2017.
 */
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new MyMazeGenerator();
        for (int i = 1; i < 2; i++) {
            Maze maze = mg.generate(i * 10, i * 10);
            maze.print();
            SearchableMaze searchableMaze = new SearchableMaze(maze);

            solveProblem(searchableMaze, new BreadthFirstSearch());
//            solveProblem(searchableMaze, new DepthFirstSearch());
//            solveProblem(searchableMaze, new BestFirstSearch());
        }
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("Solution steps: %s", solution));
//        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//        //Printing Solution Path
//        System.out.println("Solution path:");
//        ArrayList<AState> solutionPath = solution.getSolutionPath();
//        for (int i = 0; i < solutionPath.size(); i++) {
//            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
//        }
    }
}