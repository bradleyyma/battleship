package edu.colorado.caterpillars;

import edu.colorado.caterpillars.fleet.ships.Destroyer;
import edu.colorado.caterpillars.fleet.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertEquals(3, ship.getLength());
    }
}
