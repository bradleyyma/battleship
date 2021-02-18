import edu.colorado.caterpillars.Grid;
import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @BeforeEach
    public void createGridForEachPlayer() {
        Grid gridP1Upper = new Grid();
        Grid gridP2Upper = new Grid();
        Grid gridP1Lower = new Grid();
        Grid gridP2Lower = new Grid();
    }

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
        gridTest[2][4] = 1;
        gridTest[2][5] = 1;
        Ship ship1 = new Ship(1, "Battleship", 2);
        grid1.addShip(2, ship1 ,2,4,"W");
        assertArrayEquals(gridTest, grid1.getGrid("gridP2Lower"));
    }
    @Test
    public void testAttack(){
        Grid grid1 = new Grid();
        String hitOrMiss = grid1.attack(2,4,2);
        assertEquals("MISS",hitOrMiss);
    }
    @Test
    public void testDisplayGrid(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid1.getGrid("gridP2Upper"));
    }

}
