package edu.colorado.caterpillars;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BombTest {
    Bomb bomb;
    LowerGrid lower;
    UpperGrid upper;
    int [][] testLowerGrid;
    int [][] testUpperGrid;
    private SunkData sunkData;
    @BeforeEach
    public void createObjects(){
        lower = new LowerGrid();
        sunkData = new SunkData(lower.getFleet());

        upper = new UpperGrid(lower, sunkData);
        bomb = new Bomb(lower, upper, sunkData);
        Ship bat = new Battleship();
        Ship des = new Destroyer();
        lower.addShip(bat, 0, 0, "E", false); //at (0, 0), (0, 1), (0, 2), (0, 3) on surface
        lower.addShip(des, 1, 0, "E", false);  //at (1, 0), (1, 1), (1, 2)
        testLowerGrid = new int [10][10];
        testLowerGrid[0][0] = testLowerGrid[0][1] = testLowerGrid[0][3] = 2;
        testLowerGrid[0][2] = 3;
        testLowerGrid[1][0] = testLowerGrid[1][2] = 4;
        testLowerGrid[1][1] = 5;
        //int [][] testLowerGrid = {
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
        testUpperGrid = new int[10][10];
        sunkData.setNumSunk(2);

    }

    @Test
    public void testBombFire(){
        assertEquals("HIT", bomb.use(1, 1)); // Multiple hits -> return "HIT"
        testLowerGrid[0][1] *= -1;
        testLowerGrid[1][0] *= -1;
        testLowerGrid[1][2] *= -1;
        testUpperGrid[0][1] = 1;
        testUpperGrid[1][0] = 1;
        testUpperGrid[1][2] = 1;
        assertArrayEquals(testLowerGrid, lower.getGrid());
        assertArrayEquals(testUpperGrid, upper.getGrid());
    }

    @Test
    public void testBombTopLeftCorner(){
        assertEquals("HIT", bomb.use(0, 0)); // Multiple hits -> return "HIT"
        testLowerGrid[0][0] *= -1;
        testLowerGrid[1][0] *= -1;
        testLowerGrid[0][1] *= -1;
        assertArrayEquals(testLowerGrid, lower.getGrid());
        //TODO: uppergrid test
    }

    @Test
    public void testBombBottomRightCorner(){
        assertEquals("MISS", bomb.use(9, 9)); // Should be "MISS"
        assertArrayEquals(testLowerGrid, lower.getGrid());
        assertArrayEquals(testUpperGrid, upper.getGrid());
    }

    @Test
    public void testBombSingleHit(){
        assertEquals("HIT", bomb.use(2, 2)); // Should be "HIT DESTROYER"
        testLowerGrid[1][2] *= -1;
        assertArrayEquals(testLowerGrid, lower.getGrid());
        //TODO: uppergrid test
    }

    @Test
    public void testBombOutOfBounds(){
        assertThrows(IndexOutOfBoundsException.class, () -> bomb.use(5, 10));
    }

    @Test
    public void testOneSink(){
        lower.receiveAttack(1, 1); //hits the CQ of destroyer -> "MISS" due to armor
        assertEquals("SUNK Destroyer", bomb.use(2, 1)); // CQ of destroyer hit again -> sinks
        testLowerGrid[1][0] *= -1;
        testLowerGrid[1][1] *= -1;
        testLowerGrid[1][2] *= -1;
        assertArrayEquals(testLowerGrid, lower.getGrid());
        //TODO: uppergrid test
    }

    @Test
    public void testSurrender(){
        lower.receiveAttack(0, 2); // hit des CQ first time
        lower.receiveAttack(1, 1); // hit bat CQ first time
        assertEquals("SURRENDER", bomb.use(1, 2)); //Hits both ship's CQ again --> Two sinks --> Surrender
        //TODO: uppergrid test
    }

    @Test
    public void testTwoSinks(){
        lower.addShip(new Minesweeper(), 5, 5, "E", false);
        lower.receiveAttack(0, 2); // hit des CQ first time
        lower.receiveAttack(1, 1); // hit bat CQ first time
        String twoSinkRes = bomb.use(1, 2);
        assertTrue(twoSinkRes.equals("SUNK Battleship, SUNK Destroyer") || twoSinkRes.equals("SUNK Destroyer, SUNK Battleship")); //Hits both ship's CQ again --> Two sinks
        //TODO: uppergrid test
    }

    @Test
    public void testNumUses(){
        assertThrows(RuntimeException.class, () -> bomb.use(-1, 5)); // Invalid use shouldn't count as a use
        bomb.use(0, 0);
        assertThrows(Exception.class, () -> bomb.use(0, 0)); // only allowing one usage
        bomb.undoUse(0, 0);
        bomb.use(0, 0);
    }

    @Test
    public void testRequirements(){
        sunkData.setNumSunk(0);
        assertThrows(RuntimeException.class, () -> bomb.use(1, 5));
        sunkData.setNumSunk(2);
        bomb.use(1, 5);
    }

    @Test
    public void testRequirementsAfterSinkingShips(){
        sunkData.setNumSunk(0);

        upper.sendAttack(0, 2);
        upper.sendAttack(0, 2);
        upper.sendAttack(1, 1);
        upper.sendAttack(1, 1);
        bomb.use(1, 5);
    }

    @Test
    public void testUndos(){
        bomb.use(1, 1); // Multiple hits -> return "HIT"
        bomb.undoUse(1, 1);
        assertArrayEquals(testLowerGrid, lower.getGrid());
        assertArrayEquals(testUpperGrid, upper.getGrid());

        lower.receiveAttack(1, 1); // hit bat CQ first time
        assertEquals("SUNK Destroyer", bomb.use(2, 1));
        testLowerGrid[1][0] *= -1;
        testLowerGrid[1][1] *= -1;
        testLowerGrid[1][2] *= -1;
        assertArrayEquals(testLowerGrid, lower.getGrid());
        assertEquals(1, lower.getFleet().getNumSurvivingShips());

        bomb.undoUse(2, 1);
        testLowerGrid[1][0] *= -1;
        testLowerGrid[1][1] *= -1;
        testLowerGrid[1][2] *= -1;
        assertArrayEquals(testLowerGrid, lower.getGrid());
        assertEquals(2, lower.getFleet().getNumSurvivingShips());

        bomb.use(9, 9);
        assertArrayEquals(testLowerGrid, lower.getGrid());
        bomb.undoUse(9, 9);
        assertArrayEquals(testLowerGrid, lower.getGrid());
    }

    //TODO: Test edge fires, Test Return values
}
