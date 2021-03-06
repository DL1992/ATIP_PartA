package algorithms.search;

import algorithms.mazeGenerators.MyMazeGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This class is junit for bestFirstSearch
 *
 * @author Vladislav Sergienko
 * @author Doron Laadan
 */
public class UnitTestingBestFirstSearch {
    private ASearchingAlgorithm bestFirstSearch;
    private SearchableMaze searchableMaze;

    @Before
    public void setUp() throws Exception {
        System.out.println("Setting up ...");
        this.bestFirstSearch = new BestFirstSearch();
        this.searchableMaze = new SearchableMaze(new MyMazeGenerator().generate(100, 100));

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down ...");
        this.bestFirstSearch = null;
        this.searchableMaze = null;

    }

    @Test
    public void testSolveEmptyMazeShouldReturnNull() throws Exception {
        this.searchableMaze = null;
        assertNull(this.bestFirstSearch.solve(this.searchableMaze));
        this.searchableMaze = new SearchableMaze(new MyMazeGenerator().generate(-1, 2));
        assertNull(this.bestFirstSearch.solve(this.searchableMaze));
    }

    @Test(timeout = 500)
    public void testSolveTimeOut() throws Exception {
        this.bestFirstSearch.solve(this.searchableMaze);
    }

    @Test
    public void testSolveEvaluatedNodesShouldBe2() throws Exception {
        this.searchableMaze = new SearchableMaze(new MyMazeGenerator().generate(1, 2));
        this.bestFirstSearch.solve(this.searchableMaze);
        assertEquals(2, this.bestFirstSearch.getNumberOfNodesEvaluated());

    }

    @Test
    public void testGetNameShouldBeBestfirstSearch() throws Exception {
        assertEquals("Should be: Best First Search", "Best First Search", this.bestFirstSearch.getName());

    }

}