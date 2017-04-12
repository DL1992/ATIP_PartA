package test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new SimpleMazeGenerator();
//        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(100, 100);
        maze.print();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        AState state = searchableMaze.getStartState();
        System.out.println(String.format("start Position: %s", state));
        ArrayList<AState> ans = searchableMaze.getAllPossibleStates(state);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(String.format("%s. %s", i, ans.get(i)));
        }
        AState gstate = searchableMaze.getGoalState();
        System.out.println(String.format("goal Position: %s", gstate));
        ArrayList<AState> ans1 = searchableMaze.getAllPossibleStates(gstate);
        for (int i = 0; i < ans1.size(); i++) {
            System.out.println(String.format("%s. %s", i, ans1.get(i)));
        }


//        solveProblem(searchableMaze, new BreadthFirstSearch());
//        solveProblem(searchableMaze, new DepthFirstSearch());
//        solveProblem(searchableMaze, new BestFirstSearch());
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