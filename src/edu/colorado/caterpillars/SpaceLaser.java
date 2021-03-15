package edu.colorado.caterpillars;

import java.beans.PropertyChangeEvent;

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        return;
    }
}
