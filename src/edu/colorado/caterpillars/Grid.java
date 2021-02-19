package edu.colorado.caterpillars;

import java.util.Arrays;

public class Grid {

    private int[][] gridP1Upper = new int[10][10];
    private int[][] gridP1Lower = new int[10][10];
    private int[][] gridP2Upper = new int[10][10];
    private int[][] gridP2Lower = new int[10][10];
    private Fleet fleet1 = new Fleet();
    private Fleet fleet2 = new Fleet();

    public int[][] getGrid(String name){
        switch(name){
            case "gridP1Upper":
                return gridP1Upper;
            case "gridP2Upper":
                return gridP2Upper;
            case "gridP1Lower":
                return gridP1Lower;
            case "gridP2Lower":
                return gridP2Lower;
        }
        return new int[0][0];
    }

    public Fleet getFleet(int playerNum){
        if(playerNum == 1){
            return fleet1;
        }
        else if(playerNum == 2){
            return fleet2;
        }
        else{
            throw new IllegalArgumentException ("Invalid playerNum");
        }
    }

    public void addShip(int playerNum,Ship ship,int row,int col, String direction){
        int[][] grid;
        Fleet fleet;
        if (playerNum == 1){
            grid = gridP1Lower;
            fleet = fleet1;
        }
        else if (playerNum == 2){
            grid = gridP2Lower;
            fleet = fleet2;
        }
        else{
            throw new IllegalArgumentException("Invalid playerNum");
        }
        for(int i = 0; i < ship.getSize(); i++){
            if (direction == "N"){
                grid[row--][col] = ship.id;
            }
            else if (direction == "S"){ // South means increasing row num (e.g A1 then A2)
                grid[row++][col] = ship.id;
            }
            else if (direction == "E"){
                grid[row][col++] = ship.id;
            }
            else if (direction == "W"){
                grid[row][col--] = ship.id;
            }
            else{
                System.out.println("addShip is broken");
            }
        }
        fleet.addShip(ship);
    }

    public String attack(int row, int col, int playerNum){

        String hitOrMiss = "MISS";
        int [][] lower;
        int [][] upper;
        Fleet fleet;
        if (playerNum == 1){
            lower = gridP1Lower;
            upper = gridP2Upper;
            fleet = fleet1;
        }
        else{
            lower = gridP2Lower;
            upper = gridP1Upper;
            fleet = fleet2;
        }
        if(lower[row][col] > 0){ // indicates ship is at location ( a hit)
            int id = lower[row][col];
            lower[row][col] *= -1; // cannot be -1 (ship.id's will start at 2)
            upper[row][col] = 1; //indicates hit
            hitOrMiss = "HIT";
            // Interact with fleet and update ship info
            // TODO: Put in try catch
            Ship targetShip = fleet.getShipById(id);
            targetShip.hit();
            if(targetShip.isSunk()){
                hitOrMiss = "SUNK " + targetShip.getName();
            }

        }
        else{ // indicates a miss
            lower[row][col] = upper[row][col] = -1;
            hitOrMiss = "MISS";
        }
//        if(playerNum == 1) {
//            if(gridP2Lower[row][col] > 0) {
//                gridP2Lower[row][col] = gridP1Upper[row][col] = (-1 * gridP2Lower[row][col]);
//                hitOrMiss = "HIT";
//            }
//            else {
//                gridP2Lower[row][col] = gridP1Upper[row][col] = -1;
//                hitOrMiss = "MISS";
//            }
//        }
//
//        else if (playerNum == 2) {
//            if (gridP1Lower[row][col] > 0) {
//                gridP1Lower[row][col] = gridP2Upper[row][col] = (-1 * gridP1Lower[row][col]);
//                hitOrMiss = "HIT";
//            }
//            else {
//                gridP1Lower[row][col] = gridP2Upper[row][col] = -1;
//                hitOrMiss = "MISS";
//            }
//        }
//        else
//            System.out.println("Invalid Player Number.");
        return hitOrMiss;
    }

    public void displayUpper(int playerNum) {
        int[][] grid = new int [10][10];

        if(playerNum == 1)
            grid = gridP1Upper;
        else if(playerNum == 2)
            grid = gridP2Upper;
        else
            System.out.println("Invalid Player Number.");

        System.out.println(Arrays.deepToString(grid));
    }

    public void displayLower(int playerNum) {
        int[][] grid = new int [10][10];

        if(playerNum == 1)
            grid = gridP1Lower;
        else if(playerNum == 2)
            grid = gridP2Lower;
        else
            System.out.println("Invalid Player Number.");

        System.out.println(Arrays.deepToString(grid));
    }

}