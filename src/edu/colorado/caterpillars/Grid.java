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


//
//    public String attack(int row, int col, int playerNum){
//        String result;
//        int [][] lower;
//        int [][] upper;
//        Fleet fleet;
//        if (playerNum == 1){
//            lower = gridP1Lower;
//            upper = gridP2Upper;
//            fleet = fleet1;
//        }
//        else if(playerNum == 2){
//            lower = gridP2Lower;
//            upper = gridP1Upper;
//            fleet = fleet2;
//        }
//        else{
//            throw new IllegalArgumentException("Invalid playerNum");
//        }
//        if(lower[row][col] > 0){ // indicates ship is at location ( a hit)
//            int id = lower[row][col];
//            lower[row][col] *= -1; // cannot be -1 (ship.id's will start at 2)
//            upper[row][col] = 1; //indicates hit
//            result = "HIT";
//            // Interact with fleet and update ship info
//            // TODO: Put in try catch
//            Ship targetShip = fleet.getShipById(id);
//            targetShip.hit();
//            if(targetShip.isSunk()){
//                result = "SUNK " + targetShip.getName();
//                if(fleet.getNumSurvivingShips() == 0)
//                    result = "SURRENDER";
//            }
//
//        }
//        else{ // indicates a miss
//            lower[row][col] = upper[row][col] = -1;
//            result = "MISS";
//        }
//        return result;
//    }
//
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