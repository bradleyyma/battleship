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
        Fleet fleet = new Fleet();
        Ship ship1 = new Ship(4, "Battleship", 4);
        Ship ship2 = new Ship(2, "Minesweeper", 2);
        Ship ship3 = new Ship(3, "Destroyer", 3);
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
    public void testSinkThreeShips(){
        assertEquals(3,fleet.getNumSurvivingShips());
        fleet.getShipById(4).hit().hit().hit().hit();
        assertEquals(2,fleet.getNumSurvivingShips());
        fleet.getShipById(2).hit().hit();
        assertEquals(1,fleet.getNumSurvivingShips());
        fleet.getShipById(3).hit().hit().hit();
        assertEquals(0,fleet.getNumSurvivingShips());
    }
}
