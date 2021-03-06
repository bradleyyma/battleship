package edu.colorado.caterpillars;

import edu.colorado.caterpillars.fleet.*;
import edu.colorado.caterpillars.fleet.ships.Battleship;
import edu.colorado.caterpillars.fleet.ships.Destroyer;
import edu.colorado.caterpillars.fleet.ships.DummyShip;
import edu.colorado.caterpillars.fleet.ships.Minesweeper;
import edu.colorado.caterpillars.grid.LowerGrid;
import edu.colorado.caterpillars.weapons.SonarPulse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SonarPulseTest {

    private SonarPulse pulse;
    private LowerGrid grid;
    private SunkData sunkData;

    @BeforeEach
    public void createSonarPulse(){
        grid = new LowerGrid();
        sunkData = new SunkData(grid.getFleet());
        pulse = new SonarPulse(grid, sunkData);
    }

    @Test
    public void testInvalidFire(){
        assertThrows(IndexOutOfBoundsException.class, () -> pulse.fire(-1, 0));
    }

    @Test
    public void testFire(){
        int [][] testGrid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, -1, -1, 0, 0, 0},
                {0, 0, 0, -1, -1, -1, -1, -1, 0, 0},
                {0, 0, 0, 0, -1, -1, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        assertArrayEquals(testGrid, pulse.fireHelper(5,5));
    }
    @Test
    public void testFireWithShip(){
        Ship ship1 = new Battleship();
        Ship ship2 = new Battleship();
        grid.addShip(ship1, 4, 5, "E",false); //Ship is at (4,5), (4,6), (4,7), (4,8);
        grid.addShip(ship2, 6, 4, "S",false); //Ship is at (6,4), (7,4), (8,4), (9,4);

        int [][] testGrid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
                {0, 0, 0, 0, -1, 1, 1, 0, 0, 0},
                {0, 0, 0, -1, -1, -1, -1, -1, 0, 0},
                {0, 0, 0, 0, 1, -1, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        assertArrayEquals(testGrid, pulse.fireHelper(5,5));
    }

    @Test
    public void testFireOnTRCorner(){
        Ship ship1 = new Battleship();
        Ship ship2 = new Battleship();
        grid.addShip(ship1, 0, 0, "E",false); // Index starts at 0, Ship is at (4,5), (4,6), and (4,7);
        grid.addShip(ship2, 2, 0, "S",false); // Ship is at (6,4), (7,4), and (8,4);
        int [][] testGrid = {
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        assertArrayEquals(testGrid, pulse.fireHelper(0,0));
    }
    @Test
    public void testFireOnBLCorner(){
        Ship ship1 = new Battleship();
        Ship ship2 = new Minesweeper();
        grid.addShip(ship1, 9, 6, "E",false); // Index starts at 0, Ship is at (4,5), (4,6), and (4,7);
        grid.addShip(ship2, 7, 8, "E",false); // Ship is at (6,4), (7,4), and (8,4);
        int [][] testGrid = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, -1, 0},
                {0, 0, 0, 0, 0, 0, 0, -1, 1, 1},
                {0, 0, 0, 0, 0, 0, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
        };
        assertArrayEquals(testGrid, pulse.fireHelper(8,8));
    }

    @Test
    public void testStartListening(){
        sunkData.addListener(pulse);
    }

    @Test
    public void testUnlockAfter1Sunk(){
        assertTrue(pulse.isLocked());
        assertThrows(Exception.class, () -> pulse.use(5, 5));
        sunkData.setNumSunk(1);
        assertFalse(pulse.isLocked());
        pulse.use(5,5);


        sunkData.setNumSunk(2); // Should unsubscribe and stay unlocked
        assertFalse(pulse.isLocked());
    }

    @Test
    public void testUsageLimit(){
        sunkData.setNumSunk(1);
        pulse.use(5,5);
        assertThrows(RuntimeException.class, () -> pulse.use(-1, 5)); // Invalid use shouldn't count as a use
        pulse.use(5, 5);

        assertThrows(RuntimeException.class, () -> pulse.use(5, 5)); // Only 2 SonarPulses
    }

    @Test
    public void testIntegrationWithOtherClasses(){
        //SET UP
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Battleship();
        grid.addShip(ship1, 0, 0, "E",false);
        grid.addShip(ship2, 5, 5, "S",false);
        pulse = new SonarPulse(grid, sunkData); // sonar pulse object, needs LowerGrid for info
        //

        assertThrows(RuntimeException.class, () -> pulse.use(5, 5)); //first ship has not been sunk
        grid.receiveAttack(1, 0); // regular hit, no sunk
        sunkData.checkForUpdates(); // see if Subject can update
        assertThrows(RuntimeException.class, () -> pulse.use(5, 5)); //first ship has not been sunk
        grid.receiveAttack(0, 0); // hit on CQ, sunk
        sunkData.checkForUpdates(); // see if Subject can update, it can here
        pulse.use(5, 5); // should be allowed
        //make sure another sink doesn't affect usage
        grid.receiveAttack(7, 5);
        sunkData.checkForUpdates();
        grid.receiveAttack(7, 5);
        sunkData.checkForUpdates();

        // should be allowed (2 usages)
        pulse.use(5, 5);

        // Should not be allowed(this will be 3rd usage)
        assertThrows(RuntimeException.class, () -> pulse.use(5, 5));

        pulse.undoUse(5, 5);
        pulse.use(5, 5);
        assertThrows(RuntimeException.class, () -> pulse.use(5, 5));

    }

    @Test
    public void testFireWithDummyShip(){
        Ship ship1 = new DummyShip();
        Ship ship2 = new Destroyer();
        grid.addShip(ship1, 2, 2, "W", false);
        grid.addShip(ship2, 3, 3, "E", false);
        assertTrue(pulse.isLocked());
        assertThrows(Exception.class, () -> pulse.use(5, 5));
        grid.receiveAttack(2, 2);
        sunkData.checkForUpdates();
        assertTrue(pulse.isLocked());
        assertThrows(Exception.class, () -> pulse.use(4, 3));
    }


}
