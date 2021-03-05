import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;

public class SonarPulseTest {

    private SonarPulse pulse;
    private LowerGrid grid;

    @BeforeEach
    public void createSonarPulse(){
        grid = new LowerGrid();
        pulse = new SonarPulse(grid);
    }

    @Test
    public void testInvalidFire(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> pulse.fire(-1, 0));
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
        assertArrayEquals(testGrid, pulse.fire(5,5));
    }
    @Test
    public void testFireWithShip(){
        Ship ship1 = new Battleship();
        Ship ship2 = new Battleship();
        grid.addShip(ship1, 4, 5, "E"); //Ship is at (4,5), (4,6), (4,7), (4,8);
        grid.addShip(ship2, 6, 4, "S"); //Ship is at (6,4), (7,4), (8,4), (9,4);

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
        assertArrayEquals(testGrid, pulse.fire(5,5));
    }

    @Test
    public void testUseReq(){
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Minesweeper();
        grid.addShip(ship1, 0, 0, "E"); // Ship is at (0,0), (0,1)
        grid.addShip(ship2, 1, 0, "E"); // Ship is at (1, 0), (1, 0)
        pulse = new SonarPulse(grid);
        assertThrows(Exception.class, () -> pulse.use(5, 5)); // first ship has not been sunk
        grid.receiveAttack(0,1);
        assertThrows(Exception.class, () -> pulse.use(5, 5)); // first ship has not been sunk
        grid.receiveAttack(0,0);
        pulse.use(5, 5); // first ship was sunk, this should have no issues being called

        pulse.use(5, 5);
        assertThrows(Exception.class, () -> pulse.use(5, 5)); // only have 2 usages


    }


    @Test
    public void testFireOnTRCorner(){
        Ship ship1 = new Battleship();
        Ship ship2 = new Battleship();
        grid.addShip(ship1, 0, 0, "E"); // Index starts at 0, Ship is at (4,5), (4,6), and (4,7);
        grid.addShip(ship2, 2, 0, "S"); // Ship is at (6,4), (7,4), and (8,4);
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
        assertArrayEquals(testGrid, pulse.fire(0,0));
    }
    @Test
    public void testFireOnBLCorner(){
        Ship ship1 = new Battleship();
        Ship ship2 = new Minesweeper();
        grid.addShip(ship1, 9, 6, "E"); // Index starts at 0, Ship is at (4,5), (4,6), and (4,7);
        grid.addShip(ship2, 7, 8, "E"); // Ship is at (6,4), (7,4), and (8,4);
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
        assertArrayEquals(testGrid, pulse.fire(8,8));
    }

//    @Test
//    public void testUpdate(){
//        int numSunk = 1;
//
//        support.firePropertyChange("numSunk", 0, 1);
//        assertFalse(pulse.isLocked());
//    }

}
