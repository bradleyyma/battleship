package edu.colorado.caterpillars;

import edu.colorado.caterpillars.fleet.ships.DummyShip;
import edu.colorado.caterpillars.fleet.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DummyShipTest {
    private Ship ship;
    @BeforeEach
    public void createShip(){
        ship = new DummyShip();
    }

    @Test
    public void testData(){
        assertEquals("Dummy", ship.getName());
        assertEquals(1, ship.getLength());
    }
}
