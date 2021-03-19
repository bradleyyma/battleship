import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttackCommandTest {

    private AttackCommand ac;
    private Player player2;

    @BeforeEach
    public void createObjects(){
        Player player1 = new Player();
        player2 = new Player();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        Ship mine = new Minesweeper();
        player2.addShip(mine, 0, 0, "E", false);
        ac = new AttackCommand(player1, 0, 0);
    }

    @Test
    public void createAttackCommand(){
        AttackCommand ac = new AttackCommand(new Player(), 0, 0);
    }

    @Test
    public void testExecuteAndUndo(){
        int [][] testGrid = new int [10][10];
        testGrid[0][0] = -3;
        testGrid[0][1] = -2;
        ac.execute();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
        testGrid[0][0] = 3;
        testGrid[0][1] = 2;
        ac.undo();
        assertArrayEquals(testGrid, player2.getLower().getGrid());
    }
}
