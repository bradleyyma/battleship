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
        player2.addShip(mine, 0, 0, "E", false);
        ac = new AttackCommand(player1, 0, 1);
    }

    @Test
    public void createAttackCommand(){
        AttackCommand ac = new AttackCommand(new Player(), 0, 0);
    }

    @Test
    public void testAttackHitAndUndo(){
        int [][] testGrid = new int [10][10];
        testGrid[0][0] = 3;
        testGrid[0][1] = -2;
        ac.execute();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        testGrid[0][1] = 2;
        ac.undo();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
    }

    @Test
    public void testExecuteAndUndo(){
        ac = new AttackCommand(player1, 0, 0);
        int [][] testGrid = new int [10][10];
        testGrid[0][0] = -3;
        testGrid[0][1] = -2;
        ac.execute();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertEquals(0, player2.getLower().getFleet().getNumSurvivingShips());
        testGrid[0][0] = 3;
        testGrid[0][1] = 2;
        ac.undo();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        assertEquals(1, player2.getLower().getFleet().getNumSurvivingShips());
    }
}
