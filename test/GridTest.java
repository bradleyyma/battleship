import edu.colorado.caterpillars.Grid;
import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    @Test
    public void testGrid(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid1.getGrid("gridP1Upper"));
    }
    @Test
    public void testShipInGrid(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        gridTest[2][4] = 2;
        gridTest[2][5] = 2;
        Ship ship1 = new Ship(1, "Battleship", 2);
        grid1.addShip(2, ship1 ,2,4,"S");
        assertArrayEquals(gridTest, grid1.getGrid("gridP2Lower"));
    }
    @Test
    public void testHitOrMiss(){
        Grid grid1 = new Grid();
        String hitOrMiss = grid1.hitOrMiss(2,4,2);
        assertEquals("MISS",hitOrMiss);
    }
    @Test
    public void testDisplayOpponentFleet(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid1.displayOpponentFleet(2));
    }
    @Test
    public void testDisplayPlayerFleet(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid1.displayPlayerFleet(1));
    }
}
