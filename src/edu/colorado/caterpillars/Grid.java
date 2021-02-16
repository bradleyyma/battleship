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

}