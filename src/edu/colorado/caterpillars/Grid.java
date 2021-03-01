package edu.colorado.caterpillars;

import java.util.Arrays;

public class Grid {
    /* For each grid:
        0 represents nothing (no hits no misses, initial)
        -1 represents a miss (on both lower and upper grids)
        1 represents a hit (on upper grids)
        > 1 represents ship.id(on lower grids)
        < -1 represents hit on ship.id(on lower grids)
    */
    protected int[][] grid = new int[10][10];

    public int[][] getGrid(){
        return grid;
    }



//    public void displayUpper(int playerNum) {
//        int[][] grid = new int [10][10];
//
//        if(playerNum == 1)
//            grid = gridP1Upper;
//        else if(playerNum == 2)
//            grid = gridP2Upper;
//        else
//            System.out.println("Invalid Player Number.");
//
//        System.out.println(Arrays.deepToString(grid));
//    }
//
//    public void displayLower(int playerNum) {
//        int[][] grid = new int [10][10];
//
//        if(playerNum == 1)
//            grid = gridP1Lower;
//        else if(playerNum == 2)
//            grid = gridP2Lower;
//        else
//            System.out.println("Invalid Player Number.");
//
//        System.out.println(Arrays.deepToString(grid));
//    }

}