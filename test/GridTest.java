import edu.colorado.caterpillars.Grid;
import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private Grid grid;

    @BeforeEach
    public void createGrid() {
        grid = new Grid();
    }

    @Test
    public void testGrid(){
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid("gridP1Upper"));
        assertArrayEquals(gridTest, grid.getGrid("gridP1Lower"));
        assertArrayEquals(gridTest, grid.getGrid("gridP2Upper"));
        assertArrayEquals(gridTest, grid.getGrid("gridP2Lower"));
        assertThrows(Exception.class, () -> grid.getGrid("randomName"));
    }

    @Test
    public void testShipInGrid(){
        int[][] gridTest = new int[10][10];
        gridTest[2][4] = 1;
        gridTest[2][5] = 1;
        Ship ship1 = new Ship(1, "Battleship", 2);
        grid.addShip(2, ship1 ,2,4,"E");
        assertArrayEquals(gridTest, grid.getGrid("gridP2Lower"));
    }

    @Test
    public void testAttack(){
        Ship ship = new Ship(2, "Sweeps", 2);
        grid.addShip(1, ship, 0, 0, "E");
        grid.addShip(2, ship, 1, 0, "S");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i == 0 && j == 0 || i == 0 && j == 1) // where the ship is in player1's grid
                    assertNotEquals("MISS", grid.attack(i, j,1));
                else if(j == 0 && i == 1 || j == 0 && i == 2) // where the ship is in player2's grid
                    assertNotEquals("MISS", grid.attack(i, j,2));
                else { // anything else should MISS
                    assertEquals("MISS", grid.attack(i, j, 1));
                    assertEquals("MISS", grid.attack(i, j, 2));
                }
            }
        }
    }

    @Test
    public void testDisplayGrid(){
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid("gridP2Upper"));
    }
    
    @Test
    public void testSunkAndSurrender(){
        Ship ship = new Ship(2, "Minesweeper", 2);
        Ship ship2 = new Ship(3, "Destroyer", 3);
        grid.addShip(1, ship, 0, 0, "E");
        grid.addShip(1, ship2, 1, 0, "E");
        assertEquals("HIT", grid.attack(0,0, 1));
        assertEquals("SUNK Minesweeper", grid.attack(0,1, 1));
        assertEquals("HIT", grid.attack(1,0, 1));
        assertEquals("HIT", grid.attack(1,1, 1));
        assertEquals("SURRENDER", grid.attack(1,2, 1));
    }

    @Test
    public void testOutOfBoundsShip(){
        Ship ship1 = new Ship(2, "Minesweeper", 2);
        assertThrows(Exception.class, () -> grid.addShip(1, ship1, 0, 0, "N"));
    }

    @Test
    public void testOverlappingShip(){
        Ship ship1 = new Ship(2, "Minesweeper", 2);
        Ship ship2 = new Ship(3, "Destroyer", 3);
        grid.addShip(1, ship1, 0, 0, "S");
        assertThrows(Exception.class, () -> grid.addShip(1, ship2, 0, 0, "E"));
    }


}
