package edu.colorado.caterpillars;

import edu.colorado.caterpillars.fleet.ships.Battleship;
import edu.colorado.caterpillars.fleet.ships.Minesweeper;
import edu.colorado.caterpillars.fleet.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        int size = ship.getLength();
        assertEquals(4, size);
    }

    @Test
    public void testHit(){
        ship.hit();
        assertEquals(1, ship.getNumHits());
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

    @Test
    public void testMinesweeperArmor(){
        Ship ship2 = new Minesweeper();
        ship2.hitCaptainQuarters();
        assertTrue(ship2.isSunk());
    }

    @Test
    public void testArmorProtectSink(){
        ship.hit();
        assertFalse(ship.isSunk());
        ship.hit();
        assertFalse(ship.isSunk());
        ship.hit();
        assertFalse(ship.isSunk());
        ship.hit();
        assertFalse(ship.isSunk());
    }



}
