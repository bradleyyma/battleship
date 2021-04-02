package edu.colorado.caterpillars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MinesweeperTest {
    private Ship ship;
    @BeforeEach
    public void createShip(){
        ship = new Minesweeper();
    }

    @Test
    public void testData(){
        assertEquals("Minesweeper", ship.getName());
        assertEquals(2, ship.getLength());
    }
}
