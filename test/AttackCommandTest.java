import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttackCommandTest {

    private AttackCommand ac;
    private Player player2;
    private Player player1;

    @BeforeEach
    public void createObjects(){
        player1 = new Player();
        player2 = new Player();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        Ship mine = new Minesweeper();
        Ship sub = new Submarine();
        player2.addShip(mine, 3, 0, "E", false);
        player2.addShip(sub, 0, 1, "S", true); // (2, 1) (0, 0) (1, 0) (2, 0) (3, 0)
        ac = new AttackCommand(player1, 3, 1);
    }

    @Test
    public void createAttackCommand(){
        AttackCommand ac = new AttackCommand(new Player(), 0, 0);
    }

    @Test
    public void testAttackHitAndUndo(){
        int [][] testGrid = new int [10][10];
        testGrid[3][0] = 3;
        testGrid[3][1] = -2;
        ac.execute();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        testGrid[3][1] = 2;
        ac.undo();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
    }

    @Test
    public void testMultipleExecutesAndUndos(){
        ac = new AttackCommand(player1, 3, 0);
        int [][] testGrid = new int [10][10];
        int [][] testSubGrid = new int [10][10];



        ac.execute(); // Basic Attack sinks surface ship
        testGrid[3][0] = -3;
        testGrid[3][1] = -2;
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertEquals(1, player2.getLower().getFleet().getNumSurvivingShips());

        ac.execute(); // Get space laser here, "MISS" sub CQ
        testSubGrid[2][1] = testSubGrid[0][0] = testSubGrid[1][0] = testSubGrid[2][0] = 4;
        testSubGrid[3][0] = 5;
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertArrayEquals(testSubGrid, player2.getLower().getSubmergedGrid());
        assertEquals(1, player2.getLower().getFleet().getNumSurvivingShips());

        ac.execute(); // Hit CQ, sink Sub here
        testSubGrid[2][1] = testSubGrid[0][0] = testSubGrid[1][0] = testSubGrid[2][0] = -4;
        testSubGrid[3][0] = -5;
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertArrayEquals(testSubGrid, player2.getLower().getSubmergedGrid());
        assertEquals(0, player2.getLower().getFleet().getNumSurvivingShips());

        ac.undo(); // Sub should revive, CQ no armor
        testSubGrid[2][1] = testSubGrid[0][0] = testSubGrid[1][0] = testSubGrid[2][0] = 4;
        testSubGrid[3][0] = 5;
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertArrayEquals(testSubGrid, player2.getLower().getSubmergedGrid());
        assertEquals(1, player2.getLower().getFleet().getNumSurvivingShips());

        ac.execute(); // Hit CQ, sink Sub here
        testSubGrid[2][1] = testSubGrid[0][0] = testSubGrid[1][0] = testSubGrid[2][0] = -4;
        testSubGrid[3][0] = -5;
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertArrayEquals(testSubGrid, player2.getLower().getSubmergedGrid());
        assertEquals(0, player2.getLower().getFleet().getNumSurvivingShips());

        ac.undo(); // Sub should revive, CQ no armor
        testSubGrid[2][1] = testSubGrid[0][0] = testSubGrid[1][0] = testSubGrid[2][0] = 4;
        testSubGrid[3][0] = 5;
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertArrayEquals(testSubGrid, player2.getLower().getSubmergedGrid());
        assertEquals(1, player2.getLower().getFleet().getNumSurvivingShips());

        ac.undo(); // Sub should regain armor
        ac.execute(); // "MISS" sub CQ
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertArrayEquals(testSubGrid, player2.getLower().getSubmergedGrid());
        assertEquals(1, player2.getLower().getFleet().getNumSurvivingShips());

        ac.undo(); // sub should regain armor
        ac.undo(); // minesweeper should revive
        testGrid[3][0] = 3;
        testGrid[3][1] = 2;
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertEquals(2, player2.getLower().getFleet().getNumSurvivingShips());

        assertThrows(Exception.class, () -> ac.undo()); // nothing more to undo!
    }
}
