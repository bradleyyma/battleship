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
    public void testAddShips(){
        int[][] gridTest = new int[10][10];
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Destroyer();
        Ship ship3 = new Submarine();
        grid.addShip(ship1 ,2,4,"E");
        grid.addShip(ship2, 9, 9, "N");
        grid.addShip(ship3, 3, 3, "E");
        gridTest[2][4] = 3; // cid of first ship added
        gridTest[2][5] = 2; // sid of first ship added
        gridTest[8][9] = 5; // cid of second ship added
        gridTest[9][9]  = gridTest[7][9] = 4; //sid of second ship added
        gridTest[4][6] = 7; // cid of third ship added
        gridTest[4][3]  = gridTest[4][4] = gridTest[4][5] = gridTest[3][5] = 6; //sid of third ship added
        assertArrayEquals(gridTest, grid.getGrid());
    }

    @Test
    public void testOutOfBoundsShip(){
        Ship ship1 = new Destroyer();
        assertThrows(Exception.class, () -> grid.addShip(ship1, 1, 0, "N"));
        assertThrows(Exception.class, () -> grid.addShip(ship1, -1, 1, "N")); //out of bounds to start
        assertThrows(Exception.class, () -> grid.addShip(ship1, 0, 1, "X")); // invalid direction
        // Also check that the grid is correct (no partial ships were added)
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid());
        // Now check we can re add the ship(validly) correctly
        grid.addShip(ship1, 0, 0, "E");
        gridTest[0][1] = 3;
        gridTest[0][0] = gridTest[0][2] = 2;
        assertArrayEquals(gridTest, grid.getGrid());
    }

    @Test
    public void testOverlappingShip(){
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Destroyer();
        grid.addShip(ship1, 0, 0, "S");
        assertThrows(Exception.class, () -> grid.addShip(ship2, 3, 0, "N"));
        assertThrows(Exception.class, () -> grid.addShip(ship2, 0, 0, "E"));
        assertThrows(Exception.class, () -> grid.addShip(ship2, 0, 0, "S"));
        assertThrows(Exception.class, () -> grid.addShip(ship2, 0, 2, "W"));
        // Also check that the grid is correct (no partial ship2 were added)
        int[][] gridTest = new int[10][10];
        gridTest[0][0] = 3;
        gridTest[1][0] = 2; // Only ship1 should've been added
        assertArrayEquals(gridTest, grid.getGrid());
    }


    @Test
    public void testAttack(){
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid());
        Ship ship = new Minesweeper();
        grid.addShip(ship, 0, 1, "W");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i == 0 && j == 0 || i == 0 && j == 1) // where the ship is placed
                    assertNotEquals("MISS", grid.receiveAttack(i, j));
                else // anything else should MISS
                    assertEquals("MISS", grid.receiveAttack(i, j));
            }
        }
    }


    @Test
    public void testSunkAndSurrender(){
        Ship ship = new Minesweeper();
        Ship ship2 = new Destroyer();
        grid.addShip(ship, 0, 0, "E");
        grid.addShip(ship2, 1, 0, "E");
        assertEquals("SUNK Minesweeper", grid.receiveAttack(0,0));
        assertEquals("HIT", grid.receiveAttack(1,0));
        assertEquals("MISS", grid.receiveAttack(1,1));
        assertEquals("SURRENDER", grid.receiveAttack(1,1));
    }

    @Test
    public void testLowerGridValues() {
        int[][] testGrid = new int[10][10];
        int shipId1 = 2;
        int capId1 = 3;
        int shipId2 = 4;
        int capId2 = 5;
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Minesweeper();
        grid.addShip(ship1, 0, 0, "E"); //ship is at (0, 0) and (0, 1)
        grid.addShip(ship2, 1, 0, "E"); //ship is at (1, 0) and (1, 1)
        testGrid[0][0] = capId1;
        testGrid[0][1] = shipId1;
        testGrid[1][0] = capId2;
        testGrid[1][1] = shipId2;
        assertArrayEquals(testGrid, grid.getGrid());

        // This is a hit
        grid.receiveAttack(0, 0);
        testGrid[0][0] = -capId1;
        testGrid[0][1] = -shipId1; //
    }
}