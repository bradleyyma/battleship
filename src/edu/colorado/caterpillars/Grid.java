package edu.colorado.caterpillars;

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
            grid = new int[0][];
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

}