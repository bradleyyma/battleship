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
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }

    @Test
    public void addShip(){
        Ship battleship = new Battleship();
        Ship destroyer = new Destroyer();
        player1.addShip(battleship, 0, 0, "E");
        player1.addShip(destroyer, 1, 0, "S");
        int [][] testGrid = new int[10][10];
        testGrid[0][0] = testGrid[0][1] =  testGrid[0][3] = 2;
        testGrid[0][2] = 3;
        testGrid[1][0] = testGrid[3][0] = 4;
        testGrid[2][0] = 5;

        assertArrayEquals(testGrid, player1.getLowerGrid());
    }
}
