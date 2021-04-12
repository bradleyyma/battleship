package edu.colorado.caterpillars;

import edu.colorado.caterpillars.AttackBehavior.SendLaserAttack;
import edu.colorado.caterpillars.Fleet.Ships.Battleship;
import edu.colorado.caterpillars.Fleet.Ship;
import edu.colorado.caterpillars.Fleet.Ships.Submarine;
import edu.colorado.caterpillars.Grid.LowerGrid;
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

    @Test
    public void testAttackSunkBatSunkSub(){
        assertEquals("HIT",sendLaser.attack(0,0)); //hits bat
        assertEquals("HIT",sendLaser.attack(0,1)); //hits bat, "misses" sub CQ
        assertEquals("MISS",sendLaser.attack(0,5)); //misses everything
        assertEquals("HIT",sendLaser.attack(0,2)); //hits sub below, "misses" battleship CQ
        assertEquals("HIT",sendLaser.attack(0,3)); //hits both
        assertEquals("HIT",sendLaser.attack(0,4)); //hits sub WHY? does this work
        assertEquals("HIT",sendLaser.attack(1,2)); //hits sub, misses bat
        assertEquals("MISS",sendLaser.attack(5,5)); //misses everything
        assertEquals("SUNK Submarine",sendLaser.attack(0,1)); //hits sub cq -> sink
        assertEquals("SURRENDER",sendLaser.attack(0,2)); //hits bat cq -> sink -> surrender
    }
}
