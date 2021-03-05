import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        grid.addShip(ship1, 4, 5, "E"); // Index starts at 0, Ship is at (4,5), (4,6), and (4,7);
        grid.addShip(ship2, 6, 4, "S"); // Ship is at (6,4), (7,4), and (8,4);
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
}
