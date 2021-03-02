import edu.colorado.caterpillars.Fleet;
import edu.colorado.caterpillars.Grid;
import edu.colorado.caterpillars.Ship;
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
        ship1 = new Ship(4, "Battleship", 4);
        ship2 = new Ship(2, "Minesweeper", 2);
        ship3 = new Ship(3, "Destroyer", 3);
        fleet.addShip(ship1);
        fleet.addShip(ship2);
        fleet.addShip(ship3);
    }

    @Test
    public void testGetShipById(){
        Ship fleetShipId4 = fleet.getShipById(4);
        assertSame(ship1,fleetShipId4);
    }

    @Test
    public void testAccessNonexistentShip() {
        try {
            fleet.getShipById(-1); // should throw exception
            assertEquals(-1,1);
        } catch (Throwable t) {
            assert(t instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testSinkThreeShipFleet(){
        assertEquals(3,fleet.getNumSurvivingShips());

        Ship fleetShipId4 = fleet.getShipById(4);
        fleetShipId4.hit();
        fleetShipId4.hit();
        fleetShipId4.hit();
        fleetShipId4.hit();
        assertEquals(2,fleet.getNumSurvivingShips());

        Ship fleetShipId2 = fleet.getShipById(2);
        fleetShipId2.hit();
        fleetShipId2.hit();
        assertEquals(1,fleet.getNumSurvivingShips());

        Ship fleetShipId3 = fleet.getShipById(3);
        fleetShipId3.hit();
        fleetShipId3.hit();
        fleetShipId3.hit();
        assertEquals(0,fleet.getNumSurvivingShips());
    }

    @Test
    public void testSinkFleetHitById(){
        assertEquals(3,fleet.getNumSurvivingShips());

        fleet.hitShipById(4);
        fleet.hitShipById(4);
        fleet.hitShipById(4);
        fleet.hitShipById(4);
        assertEquals(2,fleet.getNumSurvivingShips());

        fleet.hitShipById(2);
        fleet.hitShipById(2);
        assertEquals(1,fleet.getNumSurvivingShips());

        fleet.hitShipById(3);
        fleet.hitShipById(3);
        fleet.hitShipById(3);
        assertEquals(0,fleet.getNumSurvivingShips());
    }
}
