import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpperGridTest {
    private UpperGrid upperGrid;
    private LowerGrid lowerGrid;

    @BeforeEach
    public void createGrid() {

        lowerGrid = new LowerGrid();
        upperGrid = new UpperGrid(lowerGrid);
        Ship ship = new Ship(2, "Minesweeper", 2);
        lowerGrid.addShip(ship, 0, 0, "E"); //Ship is at (0,0) and (0,1)
    }

    @Test
    public void testSendAttack(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(i == 0 && j == 0 || i == 0 && j == 1) // where the ship is placed
                    assertNotEquals("MISS", upperGrid.sendAttack(i, j));
                else // anything else should MISS
                    assertEquals("MISS", upperGrid.sendAttack(i, j));
            }
        }
    }

    @Test
    public void testSunkAndSurrender(){
        Ship ship2 = new Ship(3, "Destroyer", 3);
        lowerGrid.addShip(ship2, 1, 0, "E");
        assertEquals("HIT", upperGrid.sendAttack(0,0));
        assertEquals("SUNK Minesweeper", upperGrid.sendAttack(0,1));
        assertEquals("HIT", upperGrid.sendAttack(1,0));
        assertEquals("HIT", upperGrid.sendAttack(1,1));
        assertEquals("SURRENDER", upperGrid.sendAttack(1,2));
    }

    @Test
    public void testUpperGridValues(){
        int [][] testGrid = new int[10][10];
        assertArrayEquals(testGrid, upperGrid.getGrid());
        int shipId = 2; // equals id of ship created in @BeforeEach above
        // This is a hit
        upperGrid.sendAttack(0, 0);
        testGrid[0][0] = 1;
        assertArrayEquals(testGrid, upperGrid.getGrid());
        //This is a miss
        upperGrid.sendAttack(1, 0);
        testGrid[1][0] = -1;
        assertArrayEquals(testGrid, upperGrid.getGrid());
    }


}
