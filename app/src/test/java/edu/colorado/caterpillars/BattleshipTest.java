package edu.colorado.caterpillars;

import edu.colorado.caterpillars.Fleet.Ships.Battleship;
import edu.colorado.caterpillars.Fleet.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertEquals(4, ship.getLength());
        assertFalse(ship.canSubmerge());
    }
}
