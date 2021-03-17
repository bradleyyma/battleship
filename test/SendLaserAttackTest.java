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
        lower.addShip(bat, 0, 0, "E", false);
        lower.addShip(sub, 1, 4, "W", true);
        //int [][] testGrid = {
        //                {1, 1, 1, 3, 0, 0, 0, 0, 0, 0},
        //                {0, 2, 2, 2, 2, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //        };
    }

    @Test
    public void testAttackMissSurfaceHitSub(){
        assertEquals("HIT", sendLaser.attack(1, 2));
    }

    @Test
    public void testAttackHitSurfaceMissSub(){
        assertEquals("HIT",sendLaser.attack(0,0));
    }

    @Test
    public void testAttackMissSurfaceMissSub(){
        assertEquals("MISS",sendLaser.attack(8,8));
    }

    @Test
    public void testAttackHitSurfaceHitSub(){
        assertEquals("HIT",sendLaser.attack(0,3));
    }

    @Test
    public void testAttackHitSurfaceSunkSub(){
        assertEquals("HIT",sendLaser.attack(0,1));
        assertEquals("SUNK Submarine",sendLaser.attack(0,1));
    }
}
