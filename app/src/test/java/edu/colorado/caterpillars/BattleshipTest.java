package edu.colorado.caterpillars;

import edu.colorado.caterpillars.fleet.ships.Battleship;
import edu.colorado.caterpillars.fleet.Ship;
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
