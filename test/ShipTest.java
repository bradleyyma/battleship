import edu.colorado.caterpillars.Battleship;
import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ShipTest{
    private Ship ship;

    @BeforeEach
    public void createShip(){
        ship = new Battleship();
    }

    @Test
    public void testNameOfShip(){
        String name = ship.getName();
        assertEquals("Battleship", name);
    }

    @Test
    public void testSizeOfShip(){
        int size = ship.getSize();
        assertEquals(4, size);
    }

    @Test
    public void testHit(){
        ship.hit();
        assertEquals(1, ship.getNumHits());
    }

    @Test
    public void testSunk(){
        ship.hit();
        assertFalse(ship.isSunk());
        ship.hit();
        assertFalse(ship.isSunk());
        ship.hit();
        assertFalse(ship.isSunk());
        ship.hit();
        assertTrue(ship.isSunk());
    }

    @Test
    public void testGetSetIds(){
        ship.setID(1);
        assertEquals(1, ship.getID());
        ship.setCID(2);
        assertEquals(2, ship.getCID());
    }

    @Test
    public void testArmor(){
        ship.hitCaptainQuarters();
        assertFalse(ship.isSunk());
        ship.hitCaptainQuarters();
        assertTrue(ship.isSunk());
    }



}
