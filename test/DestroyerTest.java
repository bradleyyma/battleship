import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DestroyerTest {
    private Ship ship;
    @BeforeEach
    public void createShip(){
        ship = new Destroyer();
    }

    @Test
    public void testData(){
        assertEquals("Destroyer", ship.getName());
        assertEquals(3, ship.getSize());
    }
}
