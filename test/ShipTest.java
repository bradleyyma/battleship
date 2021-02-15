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

    @Test
    public void testSizeOfShip(){
        Ship ship1 = new Ship();
        ship1.setSize(2);
        int size = ship1.getSize();
        assertEquals(size, 2);
    }

    @Test
    public void testHit(){
        Ship ship1 = new Ship();
        ship1.hit();
        assertEquals(1, ship1.getNumHits());
    }

    public void testSunk(){
        Ship ship1 = new Ship();
        ship1.setSize(2);
        ship1.hit();
        ship1.hit();
        assertEquals(true, ship1.sunkStatus())
    }


}
