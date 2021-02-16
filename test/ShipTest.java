import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ShipTest{
    private Ship ship;

    @BeforeEach
    public void createShip(){
        ship = new Ship(1, "Battleship", 3);
    }

    @Test
    public void testNameOfShip(){
        String name = ship.getName();
        assertEquals("Battleship", name);
    }

    @Test
    public void testSizeOfShip(){
        int size = ship.getSize();
        assertEquals(2, size);
    }

    @Test
    public void testHit(){
        ship.hit();
        assertEquals(1, ship.getNumHits());
    }

    @Test
    public void testSunk(){
        ship.setSize(2);
        ship.hit();
        ship.hit();
        assertEquals(true, ship.sunkStatus());
    }


}
