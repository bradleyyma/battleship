import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceLaserTest {

    private SpaceLaser laser;
    @BeforeEach
    public void createObjects(){
        LowerGrid lower = new LowerGrid();
        Ship bat = new Battleship();
        Ship sub = new Submarine();
        lower.addShip(bat, 0, 0, "E", false);
        lower.addShip(sub, 1, 4, "W", true);
        laser = new SpaceLaser(lower);
    }

    @Test
    public void testAttackMissSurfaceHitSub(){
        assertEquals("HIT", laser.use(1, 2));
    }
}
