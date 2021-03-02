import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BattleshipTest {
    private Ship ship;
    @BeforeEach
    public void createShip(){
        ship = new Battleship();
    }

    @Test
    public void testData(){
        assertEquals("Battleship", ship.getName());
        assertEquals(4, ship.getSize());
    }
}
