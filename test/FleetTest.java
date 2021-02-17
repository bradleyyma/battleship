import edu.colorado.caterpillars.Fleet;
import edu.colorado.caterpillars.Grid;
import edu.colorado.caterpillars.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FleetTest {
    @BeforeEach
    public void createShip(){
        Fleet fleet = new Fleet();
    }

    @Test
    public void testFleetOfThreeBattleships(){
        Ship ship1 = new Ship(1, "Battleship", 3);
        Ship ship2 = new Ship(2, "Battleship", 3);
        Ship ship3 = new Ship(3, "Battleship", 3);
        Fleet fleet = new Fleet();
        fleet.addShip(ship1);
        fleet.addShip(ship2);
        fleet.addShip(ship3);
        assertEquals(fleet.getNumSurvivingShips(),3);

    }
}
