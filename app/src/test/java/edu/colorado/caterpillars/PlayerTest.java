package edu.colorado.caterpillars;

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
        player1.addShip(mine1, 0, 0, "E",false);
        player2.addShip(mine2, 0, 9, "S",false);
        player1.setOpponent(player2);
        player2.setOpponent(player1);
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

    @Test
    public void testSpaceLaserUpgrade(){
        player2.addShip(new Submarine(), 8, 0, "E", true);
        assertEquals("MISS",  player1.attack(9, 0));
        assertEquals("SUNK Minesweeper", player1.attack(0, 9));
        assertNotEquals(0, player2.getLower().getSubmergedGrid()[9][0]);
        assertEquals("HIT", player1.attack(9, 0));
    }

    @Test
    public void testSetMoveCommands(){
        Command moveNorth1 = new MoveFleetCommand(player1,"N");
        Command moveSouth1 = new MoveFleetCommand(player1,"S");
        Command moveEast1 = new MoveFleetCommand(player1,"E");
        Command moveWest1 = new MoveFleetCommand(player1,"W");

        Command moveNorth2 = new MoveFleetCommand(player2,"N");
        Command moveSouth2 = new MoveFleetCommand(player2,"S");
        Command moveEast2 = new MoveFleetCommand(player2,"E");
        Command moveWest2 = new MoveFleetCommand(player2,"W");

        player1.setMoveFleetCommand("N", moveNorth1);
        player1.setMoveFleetCommand("S", moveSouth1);
        player1.setMoveFleetCommand("E", moveEast1);
        player1.setMoveFleetCommand("W", moveWest1);

        player2.setMoveFleetCommand("N", moveNorth2);
        player2.setMoveFleetCommand("S", moveSouth2);
        player2.setMoveFleetCommand("E", moveEast2);
        player2.setMoveFleetCommand("W", moveWest2);
    }

    @Test
    public void testSetAttackCommands(){
        Command attack00 = new AttackCommand(player1, 0, 0);
        player1.setAttackCommand(0, 0, attack00);
    }


}
