import edu.colorado.caterpillars.Grid;
import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    @Test
    public void testGrid(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid1.getGrid("gridP1Upper"));
    }
    @Test
    public void testShipInGrid(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        gridTest[2][4] = 1;
        gridTest[2][5] = 1;
        Ship ship1 = new Ship(1, "Battleship", 2);
        grid1.addShip(2, ship1 ,2,4,"E");
        assertArrayEquals(gridTest, grid1.getGrid("gridP2Lower"));
    }
    @Test
    public void testHitOrMiss(){
        Grid grid1 = new Grid();
        Ship ship = new Ship(2, "Sweeps", 2);
        grid1.addShip(1, ship, 0, 0, "E");
        grid1.addShip(2, ship, 1, 0, "S");
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i == 0 && j == 0 || i == 0 && j == 1) // where the ship is in player1's grid
                    assertNotEquals("MISS",grid1.hitOrMiss(i,j,1));
                else if(j == 0 && i == 1 || j == 0 && i == 2) // where the ship is in player2's grid
                    assertNotEquals("MISS",grid1.hitOrMiss(i,j,2));
                else { // anything else should MISS
                    assertEquals("MISS", grid1.hitOrMiss(i, j, 1));
                    assertEquals("MISS", grid1.hitOrMiss(i, j, 2));
                }
            }
        }
    }
    @Test
    public void testDisplayOpponentFleet(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid1.displayOpponentFleet(2));
    }
    @Test
    public void testDisplayPlayerFleet(){
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid1.displayPlayerFleet(1));
    }

    @Test
    public void testSunk(){
        Grid grid = new Grid();
        Ship ship = new Ship(2, "Minesweeper", 2);
        grid.addShip(1, ship, 0, 0, "E");
        assertEquals("HIT", grid.hitOrMiss(0,0, 1));
        assertEquals("SUNK Minesweeper", grid.hitOrMiss(0,1, 1));
    }


}
