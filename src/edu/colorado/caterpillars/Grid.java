package edu.colorado.caterpillars;

import java.util.Arrays;

public class Grid {

    private int[][] gridP1Upper = new int[10][10];
    private int[][] gridP1Lower = new int[10][10];
    private int[][] gridP2Upper = new int[10][10];
    private int[][] gridP2Lower = new int[10][10];

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

    public void addShip(int playerNum,Ship ship,int row,int col, String direction){
        int[][] grid;
        if (playerNum == 1){
            grid = gridP1Lower;
        }
        else if (playerNum == 2){
            grid = gridP2Lower;
        }
        else{
            grid = new int[0][0];
        }
        for(int i = 0; i < ship.getSize(); i++){
            if (direction == "N"){
                grid[row++][col] = ship.id;
            }
            else if (direction == "S"){
                grid[row--][col] = ship.id;
            }
            else if (direction == "W"){
                grid[row][col++] = ship.id;
            }
            else if (direction == "E"){
                grid[row][col--] = ship.id;
            }
            else{
                System.out.println("addShip is broken");
            }
        }
    }
    public String attack(int row, int col, int playerNum){
        String hitOrMiss = "MISS";

        if(playerNum == 1) {
            if(gridP2Lower[row][col] > 0) {
                gridP2Lower[row][col] = gridP1Upper[row][col] = (-1 * gridP2Lower[row][col]);
                hitOrMiss = "HIT";
            }
            else {
                gridP2Lower[row][col] = gridP1Upper[row][col] = -1;
                hitOrMiss = "MISS";
            }
        }

        else if (playerNum == 2) {
            if (gridP1Lower[row][col] > 0) {
                gridP1Lower[row][col] = gridP2Upper[row][col] = (-1 * gridP1Lower[row][col]);
                hitOrMiss = "HIT";
            }
            else {
                gridP1Lower[row][col] = gridP2Upper[row][col] = -1;
                hitOrMiss = "MISS";
            }
        }
        else
            System.out.println("Invalid Player Number.");
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