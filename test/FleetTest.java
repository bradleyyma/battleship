import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FleetTest {

    private Fleet fleet;
    private Ship ship1;
    private Ship ship2;
    private Ship ship3;


    @BeforeEach
    public void createFleet(){
        fleet = new Fleet();
        ship1 = new Battleship();
        ship2 = new Minesweeper();
        ship3 = new Destroyer();
        fleet.addShip(ship1);
        fleet.addShip(ship2);
        fleet.addShip(ship3);
    }

    @Test
    public void testGetShipById(){
        Ship fleetShipId2 = fleet.getShipById(2);
        assertSame(ship1,fleetShipId2); // ship1 should get id = 2 and cid = 3
        Ship fleetCapId3 = fleet.getShipById(3);
        assertSame(ship1,fleetCapId3); // ship1 should get id = 2 and cid = 3
        Ship fleetShipId4 = fleet.getShipById(4);
        assertSame(ship2,fleetShipId4); // ship2 should get id = 4 and cid = 5
        Ship fleetCapId5 = fleet.getShipById(5);
        assertSame(ship2,fleetCapId5); // ship2 should get id = 4 and cid = 5
        Ship fleetShipId6 = fleet.getShipById(6);
        assertSame(ship3,fleetShipId6); // ship3 should get id = 6 and cid = 7
        Ship fleetCapId7 = fleet.getShipById(7);
        assertSame(ship3,fleetCapId7); // ship3 should get id = 6 and cid = 7
    }

    @Test
    public void testAccessNonexistentShip() {
        // should throw exception
        assertThrows(Exception.class, () -> fleet.getShipById(-1));
    }

    @Test
    public void testSinkThreeShipFleet(){
        assertEquals(3,fleet.getNumSurvivingShips());

        Ship fleetShipId4 = fleet.getShipById(2); // Battleship ( size 4 )
        fleetShipId4.hitCaptainQuarters();
        assertEquals(3,fleet.getNumSurvivingShips());
        fleetShipId4.hitCaptainQuarters();
        assertEquals(2,fleet.getNumSurvivingShips());

        Ship fleetShipId2 = fleet.getShipById(4); //Minesweeper ( size 2 )
        fleetShipId2.hitCaptainQuarters();
        assertEquals(1,fleet.getNumSurvivingShips());

        Ship fleetShipId3 = fleet.getShipById(6); //Destroyer ( size 3 )
        fleetShipId3.hitCaptainQuarters();
        assertEquals(1,fleet.getNumSurvivingShips());
        fleetShipId3.hitCaptainQuarters();
        assertEquals(0,fleet.getNumSurvivingShips());
    }

//    @Test
//    public void testSinkFleetHitById(){
//        assertEquals(3,fleet.getNumSurvivingShips());
//
//        fleet.hitShipById(2);
//        fleet.hitShipById(2);
//        fleet.hitShipById(2);
//        fleet.hitShipById(2);
//        assertEquals(2,fleet.getNumSurvivingShips());
//
//        fleet.hitShipById(4);
//        fleet.hitShipById(4);
//        assertEquals(1,fleet.getNumSurvivingShips());
//
//        fleet.hitShipById(6);
//        fleet.hitShipById(6);
//        fleet.hitShipById(6);
//        assertEquals(0,fleet.getNumSurvivingShips());
//    }

    @Test
    public void testHitCapQuarters(){
        assertEquals("MISS",fleet.hitShipById(3));
        assertEquals(3,fleet.getNumSurvivingShips());
        assertEquals("SUNK Battleship",fleet.hitShipById(3));
        assertEquals(2,fleet.getNumSurvivingShips());
        assertEquals("SUNK Minesweeper",fleet.hitShipById(5));
        assertEquals(1,fleet.getNumSurvivingShips());
        assertEquals("MISS",fleet.hitShipById(7));
        assertEquals(1,fleet.getNumSurvivingShips());
        assertEquals("SURRENDER",fleet.hitShipById(7));
        assertEquals(0,fleet.getNumSurvivingShips());
    }

    @Test
    public void testIdArrays(){
        assertTrue(fleet.inSidArray(2));
        assertTrue(fleet.inSidArray(4));
        assertTrue(fleet.inSidArray(6));
        assertTrue(fleet.inCidArray(3));
        assertTrue(fleet.inCidArray(5));
        assertTrue(fleet.inCidArray(7));

        assertFalse(fleet.inSidArray(3));
        assertFalse(fleet.inSidArray(5));
        assertFalse(fleet.inSidArray(7));
        assertFalse(fleet.inCidArray(2));
        assertFalse(fleet.inCidArray(4));
        assertFalse(fleet.inCidArray(6));
    }
}
