import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LowerGridTest {
    private LowerGrid grid;

    @BeforeEach
    public void createGrid(){
        grid = new LowerGrid();
    }

    @Test
    public void testAddShip(){
        int[][] gridTest = new int[10][10];
        int id1 = 2;
        int id2 = 3;
        Ship ship1 = new Ship(id1, "Minesweeper", 2);
        Ship ship2 = new Ship(id2, "Destroyer", 3);
        grid.addShip(ship1 ,2,4,"E");
        grid.addShip(ship2, 9, 9, "N");
        gridTest[2][4] = gridTest[2][5] = id1;
        gridTest[9][9] = gridTest[8][9] = gridTest[7][9] = id2;
        assertArrayEquals(gridTest, grid.getGrid());
    }

    @Test
    public void testOutOfBoundsShip(){
        Ship ship1 = new Ship(2, "Destroyer", 3);
        assertThrows(Exception.class, () -> grid.addShip(ship1, 1, 0, "N"));
        assertThrows(Exception.class, () -> grid.addShip(ship1, -1, 1, "N")); //out of bounds to start
        assertThrows(Exception.class, () -> grid.addShip(ship1, 0, 1, "X")); // invalid direction
        // Also check that the grid is correct (no partial ships were added)
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid());
    }

    @Test
    public void testOverlappingShip(){
        Ship ship1 = new Ship(2, "Minesweeper", 2);
        Ship ship2 = new Ship(3, "Destroyer", 3);
        grid.addShip(ship1, 0, 0, "S");
        assertThrows(Exception.class, () -> grid.addShip(ship2, 0, 0, "E"));
        // Also check that the grid is correct (no partial ship2 were added)
        int[][] gridTest = new int[10][10];
        gridTest[0][0] = gridTest[1][0] = 2; // Only ship1 should've been added
        assertArrayEquals(gridTest, grid.getGrid());
    }


}
