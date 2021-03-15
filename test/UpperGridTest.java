import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpperGridTest {
    private UpperGrid upperGrid;
    private LowerGrid lowerGrid;

    @BeforeEach
    public void createGrid() {
        SunkData sunkData = new SunkData();
        lowerGrid = new LowerGrid();

        upperGrid = new UpperGrid(lowerGrid, sunkData);
        Ship ship = new Minesweeper();
        lowerGrid.addShip(ship, 0, 0, "E",false); //Ship is at (0,0) and (0,1)
    }

    @Test
    public void testSendAttack(){
        assertEquals("SURRENDER", upperGrid.sendAttack(0, 0));
        assertEquals("MISS", upperGrid.sendAttack(0, 1));
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                assertEquals("MISS", upperGrid.sendAttack(i, j));
            }
        }
    }

    @Test
    public void testSunkAndSurrender(){
        Ship ship2 = new Destroyer();
        lowerGrid.addShip(ship2, 1, 0, "E",false);
        assertEquals("SUNK Minesweeper", upperGrid.sendAttack(0,0));
        assertEquals("HIT", upperGrid.sendAttack(1,0));
        assertEquals("MISS", upperGrid.sendAttack(1,1));
        assertEquals("SURRENDER", upperGrid.sendAttack(1,1));
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

    @Test
    public void testSetAttackBehavior(){
        AttackBehavior ab = new SendBasicAttack(lowerGrid);
        upperGrid.setAttackBehavior(ab);
    }



}
