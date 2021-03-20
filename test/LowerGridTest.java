import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LowerGridTest {
    private LowerGrid grid;

    @BeforeEach
    public void createGrid() {
        grid = new LowerGrid();
    }

    @Test
    public void testAddShips() {
        int[][] gridTest = new int[10][10];
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Destroyer();
        Ship ship3 = new Submarine();
        grid.addShip(ship1, 2, 4, "E", false);
        grid.addShip(ship2, 9, 9, "N", false);
        grid.addShip(ship3, 3, 3, "E", false);
        gridTest[2][4] = 3; // cid of first ship added
        gridTest[2][5] = 2; // sid of first ship added
        gridTest[8][9] = 5; // cid of second ship added
        gridTest[9][9] = gridTest[7][9] = 4; //sid of second ship added
        gridTest[4][6] = 7; // cid of third ship added
        gridTest[4][3] = gridTest[4][4] = gridTest[4][5] = gridTest[3][5] = 6; //sid of third ship added
        assertArrayEquals(gridTest, grid.getGrid());
    }

    @Test
    public void testAddSubmergedShip() {
        int[][] gridTest = new int[10][10];
        int[][] gridTest2 = new int[10][10];
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Destroyer();
        Ship ship3 = new Submarine();
        grid.addShip(ship1, 2, 4, "E", false);
        grid.addShip(ship2, 9, 9, "N", false);
        grid.addShip(ship3, 1, 4, "E", true);
        gridTest[2][4] = 3; // cid of first ship added
        gridTest[2][5] = 2; // sid of first ship added
        gridTest[8][9] = 5; // cid of second ship added
        gridTest[9][9] = gridTest[7][9] = 4; //sid of second ship added
        gridTest2[2][7] = 7; // cid of third ship added
        gridTest2[2][4] = gridTest2[2][5] = gridTest2[2][6] = gridTest2[1][6] = 6; //sid of third ship added
        assertArrayEquals(gridTest, grid.getGrid());
        assertArrayEquals(gridTest2, grid.getSubmergedGrid());
    }

    @Test
    public void testCantSubmergeDestroyer() {
        Ship ship1 = new Destroyer();
        assertThrows(Exception.class, () -> grid.addShip(ship1, 5, 5, "N", true));
    }

    @Test
    public void testOutOfBoundsShip() {
        Ship ship1 = new Destroyer();
        assertThrows(Exception.class, () -> grid.addShip(ship1, 1, 0, "N", false));
        assertThrows(Exception.class, () -> grid.addShip(ship1, -1, 1, "N", false)); //out of bounds to start
        assertThrows(Exception.class, () -> grid.addShip(ship1, 0, 1, "X", false)); // invalid direction
        // Also check that the grid is correct (no partial ships were added)
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid());
        // Now check we can re add the ship(validly) correctly
        grid.addShip(ship1, 0, 0, "E", false);
        gridTest[0][1] = 3;
        gridTest[0][0] = gridTest[0][2] = 2;
        assertArrayEquals(gridTest, grid.getGrid());
    }

    @Test
    public void testOverlappingShip() {
        Ship ship1 = new Minesweeper();
        Ship ship2 = new Destroyer();
        grid.addShip(ship1, 0, 0, "S", false);
        assertThrows(Exception.class, () -> grid.addShip(ship2, 3, 0, "N", false));
        assertThrows(Exception.class, () -> grid.addShip(ship2, 0, 0, "E", false));
        assertThrows(Exception.class, () -> grid.addShip(ship2, 0, 0, "S", false));
        assertThrows(Exception.class, () -> grid.addShip(ship2, 0, 2, "W", false));
        // Also check that the grid is correct (no partial ship2 were added)
        int[][] gridTest = new int[10][10];
        gridTest[0][0] = 3;
        gridTest[1][0] = 2; // Only ship1 should've been added
        assertArrayEquals(gridTest, grid.getGrid());
    }


    @Test
    public void testAttack() {
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid());
        Ship ship = new Minesweeper();
        grid.addShip(ship, 0, 1, "W", false);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 0 || i == 0 && j == 1) // where the ship is placed
                    assertNotEquals("MISS", grid.receiveAttack(i, j));
                else // anything else should MISS
                    assertEquals("MISS", grid.receiveAttack(i, j));
            }
        }
    }


    @Test
    public void testSunkAndSurrender() {
        Ship ship = new Minesweeper();
        Ship ship2 = new Destroyer();
        grid.addShip(ship, 0, 0, "E", false);
        grid.addShip(ship2, 1, 0, "E", false);
        assertEquals("SUNK Minesweeper", grid.receiveAttack(0, 0));
        assertEquals("HIT", grid.receiveAttack(1, 0));
        assertEquals("MISS", grid.receiveAttack(1, 1));
        assertEquals("SURRENDER", grid.receiveAttack(1, 1));
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
        grid.addShip(ship1, 0, 0, "E", false); //ship is at (0, 0) and (0, 1)
        grid.addShip(ship2, 1, 0, "E", false); //ship is at (1, 0) and (1, 1)
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

    @Test
    public void testInvalidMoveFleet(){
        assertThrows(Exception.class, () -> grid.moveFleet("NW"));
    }

    @Test
    public void testMoveFleet() {
        int[][] testGrid = new int[10][10];
        int [][] testSubGrid = new int[10][10];
        int shipId1 = 2;
        int capId1 = 3;
        int shipId2 = 4;
        int capId2 = 5;
        Ship ship1 = new Battleship();
        Ship ship2 = new Submarine();
        grid.addShip(ship1, 4, 4, "E",false); //ship is at (4, 4) (4, 5), .. (4, 7)
        grid.addShip(ship2, 4, 4, "E", true); //sub is at (4,6) (5,4) (5, 5) (5,6) (5,7)

        grid.moveFleet("E");
        testGrid[4][7] = capId1;
        testGrid[4][5] = testGrid[4][6] = testGrid[4][8] = shipId1;
        testSubGrid[4][7] = testSubGrid[5][5] = testSubGrid[5][6] = testSubGrid[5][7] = shipId2;
        testSubGrid[5][8] = capId2;
        assertArrayEquals(testGrid, grid.getGrid());
        assertArrayEquals(testSubGrid, grid.getSubmergedGrid());

        grid.moveFleet("S");
        testGrid = new int[10][10];
        testSubGrid = new int[10][10];
        testGrid[5][7] = capId1;
        testGrid[5][5] = testGrid[5][6] = testGrid[5][8] = shipId1;
        testSubGrid[5][7] = testSubGrid[6][5] = testSubGrid[6][6] = testSubGrid[6][7] = shipId2;
        testSubGrid[6][8] = capId2;
        assertArrayEquals(testGrid, grid.getGrid());
        assertArrayEquals(testSubGrid, grid.getSubmergedGrid());

        grid.moveFleet("W");
        testGrid = new int[10][10];
        testSubGrid = new int[10][10];
        testGrid[5][6] = capId1;
        testGrid[5][4] = testGrid[5][5] = testGrid[5][7] = shipId1;
        testSubGrid[5][6] = testSubGrid[6][4] = testSubGrid[6][5] = testSubGrid[6][6] = shipId2;
        testSubGrid[6][7] = capId2;
        assertArrayEquals(testGrid, grid.getGrid());
        assertArrayEquals(testSubGrid, grid.getSubmergedGrid());

        grid.moveFleet("N");
        testGrid = new int[10][10];
        testSubGrid = new int[10][10];
        testGrid[4][6] = capId1;
        testGrid[4][4] = testGrid[4][5] = testGrid[4][7] = shipId1;
        testSubGrid[4][6] = testSubGrid[5][4] = testSubGrid[5][5] = testSubGrid[5][6] = shipId2;
        testSubGrid[5][7] = capId2;
        assertArrayEquals(testGrid, grid.getGrid());
        assertArrayEquals(testSubGrid, grid.getSubmergedGrid());
    }

    @Test
    public void testOutOfBoundFleetMovementNorth(){
        Ship ship1 = new Minesweeper();
        grid.addShip(ship1, 0, 1, "E",false);
        assertThrows(Exception.class, () -> grid.moveFleet("N"));
    }

    @Test
    public void testOutOfBoundFleetMovementSouth(){
        Ship ship1 = new Minesweeper();
        grid.addShip(ship1, 9, 1, "E",false);
        assertThrows(Exception.class, () -> grid.moveFleet("S"));
    }

    @Test
    public void testOutOfBoundFleetMovementEast(){
        Ship ship1 = new Minesweeper();
        grid.addShip(ship1, 5, 8, "E",false);
        assertThrows(Exception.class, () -> grid.moveFleet("E"));
    }

    @Test
    public void testOutOfBoundFleetMovementWest(){
        Ship ship1 = new Submarine();
        grid.addShip(ship1, 5, 0, "E",true);
        assertThrows(Exception.class, () -> grid.moveFleet("W"));
    }

    @Test
    public void testMoveFleetAfterAttacks(){
        Ship ship1 = new Minesweeper();
        grid.addShip(ship1, 0, 8, "E", false);
        assertThrows(Exception.class, () -> grid.moveFleet("E"));
        grid.receiveAttack(0, 9);
        assertThrows(Exception.class, () -> grid.moveFleet("E"));
    }

    @Test
    public void testUndoStack(){
        grid.addGridToHistory();
        grid.addShip(new Minesweeper(), 0, 0, "E", false);
        int [][] testGrid = new int [10][10];
        testGrid[0][0] = 3;
        testGrid[0][1] = 2;
        assertArrayEquals(testGrid, grid.getGrid());

        int [][] emptyGrid = new int [10][10];
        grid.undoGrids();
        assertArrayEquals(emptyGrid, grid.getGrid());

    }
}

