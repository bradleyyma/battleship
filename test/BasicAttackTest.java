import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAttackTest {
    private BasicAttack basicAttack;
    private LowerGrid lower;
    @BeforeEach
    public void createObject(){
        lower = new LowerGrid();
        Ship bat = new Battleship();
        Ship sub = new Submarine();
        lower.addShip(bat, 0, 0, "E", false); //at (0, 0), (0, 1), (0, 2), (0, 3) on surface
        lower.addShip(sub, 1, 4, "W", true);  //at (0, 1), (0, 2), (0, 3), (0, 4), (1, 2) submerged
        basicAttack = new BasicAttack(lower);
    }

    @Test
    public void testMissOnSurface(){
        // basic attack should only deal with surfaced ships
        assertEquals("MISS", basicAttack.use(0, 4));
        assertEquals("MISS", basicAttack.use(1, 2));
    }

    @Test
    public void testHitOnSurface(){
        assertEquals("HIT", basicAttack.use(0, 0));
        assertEquals("HIT", basicAttack.use(0, 1));
        assertEquals("HIT", basicAttack.use(0, 3));
    }

    @Test
    public void testSink(){
        assertEquals("MISS", basicAttack.use(0, 2));
        assertEquals("SUNK Battleship", basicAttack.use(0, 2)); // no surrender due to submarine
    }

    @Test
    public void testUndoUse(){
        basicAttack.use(0, 2);
        basicAttack.use(0, 2);
        assertEquals(1, lower.getFleet().getNumSurvivingShips());
        basicAttack.undoUse(0, 2);
        assertEquals(2, lower.getFleet().getNumSurvivingShips());

    }
}
