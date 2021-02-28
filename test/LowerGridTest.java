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
    public void testShipInGrid(){
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


}
