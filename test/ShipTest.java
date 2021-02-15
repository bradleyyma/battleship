import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShipTest{
    @Test
    public void createShip(){
        Ship ship1 = new Ship();
    }

    @Test
    public void testNameOfShip(){
        Ship ship1 = new Ship();
        ship1.setName("Battleship");
        String name = ship1.getName();
        assertEquals(name, "Battleship");
    }


}
