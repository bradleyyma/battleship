import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player1;
    private Player player2;
    @BeforeEach
    public void createPlayer(){
        player1 = new Player();
        player2 = new Player();
        Ship mine1 = new Minesweeper();
        Ship mine2  = new Minesweeper();
        player1.addShip(mine1, 0, 0, "E");
        player2.addShip(mine2, 0, 9, "S");
        player1.setOpponent(player2, new SunkData());
        player2.setOpponent(player1, new SunkData());
    }

    @Test
    public void testLowerGrid(){
        int [][] testGrid = new int[10][10];
        testGrid[0][1] = 2;
        testGrid[0][0] = 3;
        assertArrayEquals(testGrid, player1.getLower().getGrid());
    }

    @Test
    public void testAttack(){
        assertEquals("HIT", player1.attack(1, 9));
        assertEquals("SURRENDER", player2.attack(0, 0));
    }

    @Test
    public void testGrids(){
        int [][] testLowerGrid = new int[10][10];
        testLowerGrid[0][0] = 3;
        testLowerGrid[0][1] = 2;
        int [][] testUpperGrid = new int[10][10];
        assertArrayEquals(testUpperGrid, player2.getUpper().getGrid());
        player2.attack(0, 1);
        testLowerGrid[0][1] = -2;
        testUpperGrid[0][1] = 1;
        assertArrayEquals(testLowerGrid, player1.getLower().getGrid());
        assertArrayEquals(testUpperGrid, player2.getUpper().getGrid());
    }


}
