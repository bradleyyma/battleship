import edu.colorado.caterpillars.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MovesCommandTest {
    @Test
    public void testMoveEastAndUndo(){
        Player player = new Player();
        Ship ship = new Destroyer();
        String dir = "E";
        player.addShip(ship,1,1,dir,false);
        MoveFleetCommand moveEastCommand = new MoveFleetCommand(player,dir);
        int[][] gridTest = new int[10][10];
        gridTest[1][1] = 2;
        gridTest[1][2] = 3;
        gridTest[1][3] = 2;
        assertArrayEquals(gridTest, player.getLower().getGrid());

        moveEastCommand.execute();
        gridTest[1][1] = 0;
        gridTest[1][2] = 2;
        gridTest[1][3] = 3;
        gridTest[1][4] = 2;
        assertArrayEquals(gridTest, player.getLower().getGrid());

        moveEastCommand.undo();
        gridTest[1][1] = 2;
        gridTest[1][2] = 3;
        gridTest[1][3] = 2;
        gridTest[1][4] = 0;
        assertArrayEquals(gridTest, player.getLower().getGrid());
    }
}
