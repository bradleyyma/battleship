import edu.colorado.caterpillars.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    @Test
    public void testInititializeGrid() {
        Grid grid1 = new Grid();
        int[][] gridTest = new int[10][10];
        assertEquals(gridTest, grid1.getGrid("gridP1Upper"));
    }
}
