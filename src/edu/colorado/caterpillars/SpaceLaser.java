package edu.colorado.caterpillars;

public class SpaceLaser extends Weapon{
    private int [][] grid;
    private Fleet fleet;

    public SpaceLaser(LowerGrid lower){
        grid = lower.getGrid();
        fleet = lower.getFleet();
    }

    public String use(int row, int col){
        return "HIT";
    }
}
