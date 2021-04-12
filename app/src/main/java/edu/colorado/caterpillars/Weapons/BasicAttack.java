package edu.colorado.caterpillars.Weapons;

import edu.colorado.caterpillars.Fleet.Fleet;
import edu.colorado.caterpillars.Grid.LowerGrid;
import edu.colorado.caterpillars.Fleet.Ship;

public class BasicAttack extends Weapon {
    private LowerGrid lower;
    private int [][] grid;
    private Fleet fleet;
    public BasicAttack(LowerGrid lower){
        this.lower = lower;
        grid = lower.getGrid();
        fleet = lower.getFleet();
    }

    public String fire(int row, int col){
        lower.addGridsToHistory();
        if(grid[row][col] > 0){
            int id = grid[row][col];
            String result = fleet.hitShipById(id);
            if(!result.equals("MISS")){
                grid[row][col] = -id; // -ship.id represents a ship coord that has been hit (should be <= -2)
            }
            if(result.contains("SUNK") || result.equals("SURRENDER")){
                Ship ship = fleet.getShipById(id);
                int sid = ship.getID();
//                int cid = ship.getCID();
                int h = grid.length;
                int w = grid[0].length;
                for(int i = 0; i < h; i++){
                    for(int j = 0; j < w; j++){
                        if(grid[i][j] == sid) {
                            grid[i][j] = -sid;
                        }
//                        else if(grid[i][j] == cid){
//                            grid[i][j] = -cid;
//                        }
                    }
                }

            }
            return result;
        }
        else{
            if(grid[row][col] > -2) // sunk ship ids should not be changed
                grid[row][col] = -1; // -1 should indicate miss
            return "MISS";
        }
    }

    @Override
    public void undoUse(int row, int col) {
        lower.undoGrids();
        int id = lower.getGrid()[row][col];
        if(id > 0){
            fleet.undoHitShipById(id);
        }
    }
}
