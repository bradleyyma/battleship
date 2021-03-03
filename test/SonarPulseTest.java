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
        grid.addShip(ship1, 4, 5, "E"); //Ship is at (4,5), (4,6), and (4,7);
        grid.addShip(ship2, 6, 4, "S"); //Ship is at (6,4), (7,4), and (8,4);
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
}
