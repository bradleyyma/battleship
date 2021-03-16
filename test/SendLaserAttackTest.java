import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SendLaserAttackTest {
    private SendLaserAttack sendLaser;
    @BeforeEach
    public void createObject(){
        LowerGrid lower = new LowerGrid();
        Ship bat = new Battleship();
        Ship sub = new Submarine();
        sendLaser = new SendLaserAttack(lower);
    }

    @Test
    public void testAttackMissSurfaceHitSub(){
        LowerGrid lower = new LowerGrid();
        Ship bat = new Battleship();
        Ship sub = new Submarine();
        lower.addShip(bat, 0, 0, "E", false);
        lower.addShip(sub, 1, 4, "W", true);
        assertEquals("HIT", sendLaser.attack(1, 2));
    }

    @Test
    public void testAttackHitSurfaceMissSub(){
        LowerGrid lower = new LowerGrid();
        Ship bat = new Battleship();
        Ship sub = new Submarine();
        lower.addShip(bat, 0, 0, "E", false);
        lower.addShip(sub, 1, 4, "W", true);
        assertEquals("HIT",sendLaser.attack(0,0));
    }

    @Test
    public void testAttackMissSurfaceMissSub(){
        LowerGrid lower = new LowerGrid();
        Ship bat = new Battleship();
        Ship sub = new Submarine();
        lower.addShip(bat, 0, 0, "E", false);
        lower.addShip(sub, 1, 4, "W", true);
        assertEquals("MISS",sendLaser.attack(9,0));
    }
}
