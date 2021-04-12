package edu.colorado.caterpillars;

import edu.colorado.caterpillars.Fleet.Ships.Destroyer;
import edu.colorado.caterpillars.Fleet.Ship;
import edu.colorado.caterpillars.GameCommands.MoveFleetCommand;
import edu.colorado.caterpillars.Main.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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
    @Test
    public void testMoveWestNorthAndUndo(){
        Player player = new Player();
        Ship ship = new Destroyer();
        player.addShip(ship,1,1,"E",false);
        MoveFleetCommand moveWestCommand = new MoveFleetCommand(player,"W");
        MoveFleetCommand moveNorthCommand = new MoveFleetCommand(player,"N");
        int[][] gridTest = new int[10][10];
        gridTest[1][1] = 2;
        gridTest[1][2] = 3;
        gridTest[1][3] = 2;
        assertArrayEquals(gridTest, player.getLower().getGrid());

        moveWestCommand.execute();
        moveNorthCommand.execute();
        gridTest[1][1] = 0;
        gridTest[1][2] = 0;
        gridTest[1][3] = 0;
        gridTest[0][0] = 2;
        gridTest[0][1] = 3;
        gridTest[0][2] = 2;
        assertArrayEquals(gridTest, player.getLower().getGrid());

        moveNorthCommand.undo();
        gridTest[0][0] = 0;
        gridTest[0][1] = 0;
        gridTest[0][2] = 0;
        gridTest[1][0] = 2;
        gridTest[1][1] = 3;
        gridTest[1][2] = 2;
        assertArrayEquals(gridTest, player.getLower().getGrid());

        moveWestCommand.undo();
        gridTest[1][0] = 0;
        gridTest[1][1] = 2;
        gridTest[1][2] = 3;
        gridTest[1][3] = 2;

        assertArrayEquals(gridTest, player.getLower().getGrid());
    }
}
