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
        assertEquals(3, size);
    }

    @Test
    public void testHit(){
        ship.hit();
        assertEquals(1, ship.getNumHits());
    }

    @Test
    public void testSunk(){
        ship.hit();
        assertEquals(false, ship.isSunk());
        ship.hit();
        assertEquals(false, ship.isSunk());
        ship.hit();
        assertEquals(true, ship.isSunk());
    }

    @Test
    public void testGetSetIds(){
        ship.setID(1);
        assertEquals(1, ship.getID());
        ship.setCID(2);
        assertEquals(2, ship.getCID());
    }



}
