package edu.colorado.caterpillars;

import edu.colorado.caterpillars.grid.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GridTest {

    private Grid grid;

    @BeforeEach
    public void createGrid() {
        grid = new Grid();
    }

    @Test
    public void testGetGrid(){
        int[][] gridTest = new int[10][10];
        assertArrayEquals(gridTest, grid.getGrid());
    }

}
