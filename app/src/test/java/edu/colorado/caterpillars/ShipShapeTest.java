package edu.colorado.caterpillars;

import edu.colorado.caterpillars.fleet.ShipShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShipShapeTest {
    ShipShape battleshipShape;
    ShipShape submarineShape;
    @BeforeEach
    public void createObject(){
        battleshipShape = new ShipShape(new int[][]{{1, 1, 2, 1}});
        submarineShape = new ShipShape(new int[][]{{0, 0, 1, 0}, {1, 1, 1, 2}});
    }

    @Test
    public void testGetWidth(){
        assertEquals(2, submarineShape.getWidth());
        assertEquals(1, battleshipShape.getWidth());
    }

    @Test
    public void testGetLength(){
        assertEquals(4, battleshipShape.getLength());
        assertEquals(4, submarineShape.getLength());
    }

    @Test
    public void testGetShape(){
        int id = ShipShape.id;
        int cid = ShipShape.cid;
        int [][] testBat = {{id, id, cid, id}};
        int [][] testSub = {{0, 0, id, 0}, {id, id, id, cid}};
        assertArrayEquals(testSub, submarineShape.getShape());
        assertArrayEquals(testBat, battleshipShape.getShape());
    }
}
