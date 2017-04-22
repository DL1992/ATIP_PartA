package algorithms.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 22/04/2017.
 */
public class UnitTestingBestFirstSearch {
    ASearchingAlgorithm bestFirstSearch;

    @Before
    public void setUp() throws Exception {
        System.out.println("Setting up ...");
        this.bestFirstSearch = new BestFirstSearch();

    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down ...");
        this.bestFirstSearch = null;

    }

    @Test
    public void solve() throws Exception {

    }

    @Test
    public void testGetNameShouldBeBestfirstSearch() throws Exception {
        assertEquals("Should be: Best First Search", "Best First Search", this.bestFirstSearch.getName());

    }

}