package edu.colorado.caterpillars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BombTest {
    Bomb bomb;
    LowerGrid lower;
    int [][] testGrid;
    @BeforeEach
    public void createObjects(){
        lower = new LowerGrid();
        bomb = new Bomb(lower);
        Ship bat = new Battleship();
        Ship des = new Destroyer();
        lower.addShip(bat, 0, 0, "E", false); //at (0, 0), (0, 1), (0, 2), (0, 3) on surface
        lower.addShip(des, 1, 0, "E", false);  //at (1, 0), (1, 1), (1, 2)
        testGrid = new int [10][10];
        testGrid[0][0] = testGrid[0][1] = testGrid[0][3] = 2;
        testGrid[0][2] = 3;
        testGrid[1][0] = testGrid[1][2] = 4;
        testGrid[1][1] = 5;
        //int [][] testGrid = {
        //                {2, 2, 3, 2, 0, 0, 0, 0, 0, 0},
        //                {4, 5, 4, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //        };
    }

    @Test
    public void testBombFire(){
        assertEquals("HIT", bomb.use(1, 1)); // Should be "HIT BATTLESHIP AND SUNK DESTROYER" right?
        testGrid[0][1] *= -1;
        testGrid[1][0] *= -1;
        testGrid[1][2] *= -1;
        assertArrayEquals(testGrid, lower.getGrid());
    }

    @Test
    public void testBombTopLeftCorner(){
        assertEquals("HIT", bomb.use(0, 0)); // Should be "HIT BATTLESHIP AND HIT DESTROYER" right?
        testGrid[0][0] *= -1;
        testGrid[1][0] *= -1;
        testGrid[0][1] *= -1;
        assertArrayEquals(testGrid, lower.getGrid());
    }

    @Test
    public void testBombBottomRightCorner(){
        assertEquals("MISS", bomb.use(9, 9)); // Should be "MISS"
        testGrid[9][9] *= 0;
        testGrid[8][9] *= 0;
        testGrid[9][8] *= 0;
        assertArrayEquals(testGrid, lower.getGrid());
    }

    @Test
    public void testBombSingleHit(){
        assertEquals("HIT", bomb.use(2, 2)); // Should be "HIT DESTROYER"
        testGrid[1][2] *= -1;
        testGrid[2][2] *= 0;
        testGrid[3][2] *= 0;
        testGrid[2][3] *= 0;
        testGrid[2][1] *= 0;
        assertArrayEquals(testGrid, lower.getGrid());
    }

    @Test
    public void testBombOutOfBounds(){
        assertThrows(IndexOutOfBoundsException.class, () -> bomb.use(5, 10));
    }

    //TODO: Test edge fires, Test Return values
}
