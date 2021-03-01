import edu.colorado.caterpillars.Grid;
import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private Grid grid;

    @BeforeEach
    public void createGrid() {
        grid = new Grid();
    }

    @Test
    public void testGetGrid(){
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid());
    }



//    @Test
//    public void testDisplayGrid(){
//        // TODO: Actually test the display (console output)
//        int[][] gridTest = new int[10][10];
//        assertArrayEquals(gridTest, grid.getGrid("gridP2Upper"));
//    }
//
//
//
//    @Test
//    public void testOutOfBoundsShip(){
//        Ship ship1 = new Ship(2, "Destroyer", 3);
//        assertThrows(Exception.class, () -> grid.addShip(1, ship1, 1, 0, "N"));
//        // Also check that the grid is correct (no partial ships were added)
//        int[][] gridTest = new int[10][10];
//        assertArrayEquals(gridTest, grid.getGrid("gridP1Lower"));
//    }
//
//    @Test
//    public void testOverlappingShip(){
//        Ship ship1 = new Ship(2, "Minesweeper", 2);
//        Ship ship2 = new Ship(3, "Destroyer", 3);
//        grid.addShip(1, ship1, 0, 0, "S");
//        assertThrows(Exception.class, () -> grid.addShip(1, ship2, 0, 0, "E"));
//        // Also check that the grid is correct (no partial ship2 were added)
//        int[][] gridTest = new int[10][10];
//        gridTest[0][0] = gridTest[1][0] = 2; // Only ship1 should've been added
//        assertArrayEquals(gridTest, grid.getGrid("gridP1Lower"));
//    }
//
//    @Test
//    public void testGridsAfterOneRound(){
//        int [][]testP1Lower = new int[10][10];
//        int [][]testP2Lower = new int[10][10];
//        int [][]testP1Upper = new int[10][10];
//        int [][]testP2Upper = new int[10][10];
//        Ship ship1 = new Ship(2, "Minesweeper", 2);
//        grid.addShip(1, ship1, 0, 0, "E"); //ship is at (0, 0) and (0, 1)
//        grid.addShip(2, ship1, 0, 0, "S"); //ship is at (0, 0) and (1, 0)
//        testP1Lower[0][0] = testP1Lower[0][1] = 2;
//        assertArrayEquals(testP1Lower, grid.getGrid("gridP1Lower"));
//        testP2Lower[0][0] = testP2Lower[1][0] = 2;
//        assertArrayEquals(testP2Lower, grid.getGrid("gridP2Lower"));
//        // This is a hit
//        grid.attack(0, 0, 2);
//        testP2Lower[0][0] = -2; // a hit on ship with id 2
//        testP1Upper[0][0] = 1; // a hit
//        assertArrayEquals(testP2Lower, grid.getGrid("gridP2Lower"));
//        assertArrayEquals(testP1Upper, grid.getGrid("gridP1Upper"));
//        //This is a miss
//        grid.attack(1, 0, 1);
//        testP1Lower[1][0] = -1; // miss
//        testP2Upper[1][0] = -1; // miss
//        assertArrayEquals(testP1Lower, grid.getGrid("gridP1Lower"));
//        assertArrayEquals(testP2Upper, grid.getGrid("gridP2Upper"));
//    }


}
