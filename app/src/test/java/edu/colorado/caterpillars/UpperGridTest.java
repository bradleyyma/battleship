package edu.colorado.caterpillars;

import edu.colorado.caterpillars.AttackBehavior.AttackBehavior;
import edu.colorado.caterpillars.AttackBehavior.SendBasicAttack;
import edu.colorado.caterpillars.fleet.ships.Destroyer;
import edu.colorado.caterpillars.fleet.ships.Minesweeper;
import edu.colorado.caterpillars.fleet.Ship;
import edu.colorado.caterpillars.fleet.ships.Submarine;
import edu.colorado.caterpillars.grid.LowerGrid;
import edu.colorado.caterpillars.grid.UpperGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpperGridTest {
    private UpperGrid upperGrid;
    private LowerGrid lowerGrid;

    @BeforeEach
    public void createGrid() {
        lowerGrid = new LowerGrid();
        SunkData sunkData = new SunkData(lowerGrid.getFleet());
        upperGrid = new UpperGrid(lowerGrid, sunkData);
        Ship ship = new Minesweeper();
        lowerGrid.addShip(ship, 0, 0, "E",false); //Ship is at (0,0) and (0,1)

    }

    @Test
    public void testSendAttack(){
        assertEquals("SURRENDER", upperGrid.sendAttack(0, 0));
        assertEquals("MISS", upperGrid.sendAttack(0, 1));
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                assertEquals("MISS", upperGrid.sendAttack(i, j));
            }
        }
    }

    @Test
    public void testSunkAndSurrender(){
        Ship ship2 = new Destroyer();
        lowerGrid.addShip(ship2, 1, 0, "E",false);
        assertEquals("SUNK Minesweeper", upperGrid.sendAttack(0,0));
        assertEquals("HIT", upperGrid.sendAttack(1,0));
        assertEquals("MISS", upperGrid.sendAttack(1,1));
        assertEquals("SURRENDER", upperGrid.sendAttack(1,1));
    }

    @Test
    public void testUpperGridValues(){
        int [][] testGrid = new int[10][10];
        assertArrayEquals(testGrid, upperGrid.getGrid());
        int shipId = 2; // equals id of ship created in @BeforeEach above
        // This is a hit
        upperGrid.sendAttack(0, 0);
        testGrid[0][0] = 1;
        assertArrayEquals(testGrid, upperGrid.getGrid());
        //This is a miss
        upperGrid.sendAttack(1, 0);
        testGrid[1][0] = -1;
        assertArrayEquals(testGrid, upperGrid.getGrid());
    }

    @Test
    public void testSetAttackBehavior(){
        AttackBehavior ab = new SendBasicAttack(lowerGrid);
        upperGrid.setAttackBehavior(ab);
    }

    @Test
    public void testAttackBehaviorSwitchesAfterFirstSunk(){
        lowerGrid.addShip(new Submarine(), 1, 3, "W", true); // submerged sub at (0, 0){CQ}, (0, 1), (0, 2), (0, 3), (1, 1)
        //No Minesweeper, but Submarine at coordinates
        //Should miss since attack is BasicAttack, cannot hit submerged ship
        assertEquals("MISS", upperGrid.sendAttack(0, 2));
        assertEquals("MISS", upperGrid.sendAttack(0, 3));
        assertEquals("MISS", upperGrid.sendAttack(1, 1));

        //Sink Minesweeper to get new SpaceLaser behavior, and test to see if submarine can now be hit
        assertEquals("SUNK Minesweeper", upperGrid.sendAttack(0,0));

        assertEquals("HIT", upperGrid.sendAttack(0, 1));
        assertEquals("HIT", upperGrid.sendAttack(0, 2));
        assertEquals("HIT", upperGrid.sendAttack(0, 3));
        assertEquals("HIT", upperGrid.sendAttack(1, 1));
        assertEquals("MISS", upperGrid.sendAttack(0, 0)); // CQ first "miss"
        assertEquals("SURRENDER", upperGrid.sendAttack(0, 0)); // CQ hit -> sunk -> surrender
    }




}
