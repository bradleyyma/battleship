package edu.colorado.caterpillars.grid;

public class Grid {

    final protected int COLS = 10;
    final protected int ROWS = 10;
    protected int[][] grid = new int[ROWS][COLS];

    public int[][] getGrid(){
        return grid;
    }
}