package edu.colorado.caterpillars.weapons;

import edu.colorado.caterpillars.fleet.Fleet;
import edu.colorado.caterpillars.grid.LowerGrid;
import edu.colorado.caterpillars.fleet.Ship;

public class SpaceLaser extends Weapon {
    private LowerGrid lower;

    public SpaceLaser(LowerGrid lower){
        this.lower = lower;
    }

    private String helper(int [][] grid, int row, int col){
        Fleet fleet = lower.getFleet();
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

    public String fire(int row, int col){
        lower.addGridsToHistory();
        String surfaceResult = helper(lower.getGrid(), row, col);
        String submergeResult = helper(lower.getSubmergedGrid(), row, col);
        if(surfaceResult.equals("SURRENDER") || submergeResult.equals("SURRENDER"))
            return "SURRENDER";
        if(surfaceResult.split(" ")[0].equals("SUNK"))
            return surfaceResult;
        if(submergeResult.split(" ")[0].equals("SUNK"))
            return submergeResult;
        if(surfaceResult.equals("HIT") || submergeResult.equals("HIT"))
            return "HIT";
        else
            return "MISS";
    }

    @Override
    public void undoUse(int row, int col) {
        lower.undoGrids();
        Fleet fleet = lower.getFleet();
        int surfaceId = lower.getGrid()[row][col];
        int subId = lower.getSubmergedGrid()[row][col];
        if(surfaceId > 0){
            fleet.undoHitShipById(surfaceId);
        }
        if(subId > 0){
            fleet.undoHitShipById(subId);
        }
    }
}
