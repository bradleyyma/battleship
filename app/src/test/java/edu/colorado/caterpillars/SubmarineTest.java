package edu.colorado.caterpillars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubmarineTest {
    private Ship ship;
    @BeforeEach
    public void createShip(){
        ship = new Submarine();
    }

    @Test
    public void testData(){
        assertEquals("Submarine", ship.getName());
        assertEquals(4, ship.getLength());
        assertEquals(2, ship.getWidth());
        assertTrue(ship.canSubmerge());
    }
}
