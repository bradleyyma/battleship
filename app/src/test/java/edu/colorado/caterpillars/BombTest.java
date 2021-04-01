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
    }

    @Test
    public void testBombFire(){
        assertEquals("HIT", bomb.use(1, 1));
        testGrid[0][1] *= -1;
        testGrid[1][0] *= -1;
        testGrid[1][2] *= -1;
        assertArrayEquals(testGrid, lower.getGrid());
    }

    //TODO: Test edge fires, Test Return values
}
