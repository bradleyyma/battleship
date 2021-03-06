package edu.colorado.caterpillars;

import edu.colorado.caterpillars.fleet.ships.Battleship;
import edu.colorado.caterpillars.fleet.Ship;
import edu.colorado.caterpillars.fleet.ships.Submarine;
import edu.colorado.caterpillars.grid.LowerGrid;
import edu.colorado.caterpillars.weapons.SpaceLaser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpaceLaserTest {

    private SpaceLaser laser;
    private LowerGrid lower;
    @BeforeEach
    public void createObjects(){
        lower = new LowerGrid();
        Ship bat = new Battleship();
        Ship sub = new Submarine();
        lower.addShip(bat, 0, 0, "E", false); //at (0, 0), (0, 1), (0, 2){CQ}, (0, 3) on surface
        lower.addShip(sub, 1, 4, "W", true);  //at (0, 1){CQ}, (0, 2), (0, 3), (0, 4), (1, 2) submerged
        laser = new SpaceLaser(lower);
    }

    @Test
    public void testAttackMissSurfaceHitSub(){
        assertEquals("HIT", laser.use(1, 2));
    }

    @Test
    public void testSinkSub(){
        assertEquals("HIT", laser.use(0, 1)); // Hits the surface, "hits" cq of sub, so returns "HIT"
        assertEquals("SUNK Submarine", laser.use(0, 1)); // armor of sub should none here, so CQ should result in sunk
    }

    @Test
    public void testSurrenderSurfaceThenSub(){
        laser.use(0, 2);
        assertEquals("SUNK Battleship", laser.use(0, 2)); // sink surface ship
        laser.use(0, 1);
        assertEquals("SURRENDER", laser.use(0, 1)); //sink submarine => Surrender
    }

    @Test
    public void testSurrenderSubThenSurface(){
        laser.use(0, 1);
        assertEquals("SUNK Submarine", laser.use(0, 1)); //sink submarine
        laser.use(0, 2);
        assertEquals("SURRENDER", laser.use(0, 2)); // sink surface ship => Surrender

    }

    @Test
    public void testUseThenUndo(){
        int[][] testGrid = new int[10][10];
        testGrid[0][2] = 3;
        testGrid[0][0] = testGrid[0][1] = testGrid[0][3] = 2;
        int[][] testSubGrid = new int[10][10];
        testSubGrid[0][2] = testSubGrid[0][4] = testSubGrid[0][3] = testSubGrid[1][2] = 4;
        testSubGrid[0][1] = 5;
        assertArrayEquals(testGrid, lower.getGrid());
        assertArrayEquals(testSubGrid, lower.getSubmergedGrid());

        laser.use(0, 1);
        testGrid[0][1] = -2;
        assertArrayEquals(testGrid, lower.getGrid());
        assertArrayEquals(testSubGrid, lower.getSubmergedGrid());

        laser.use(0, 1);
        testSubGrid[0][2] = testSubGrid[0][4] = testSubGrid[0][3] = testSubGrid[1][2] = -4;
        testSubGrid[0][1] = -5;
        assertEquals(1, lower.getFleet().getNumSurvivingShips());


        laser.undoUse(0, 1);
        testSubGrid[0][2] = testSubGrid[0][4] = testSubGrid[0][3] = testSubGrid[1][2] = 4;
        testSubGrid[0][1] = 5;
        assertEquals(2, lower.getFleet().getNumSurvivingShips());
    }
}
