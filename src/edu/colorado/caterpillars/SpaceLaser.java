package edu.colorado.caterpillars;

public class SpaceLaser extends Weapon{
    private LowerGrid lower;

    public SpaceLaser(LowerGrid lower){
        this.lower = lower;
    }

    private String helper(int [][] grid, int row, int col){
        Fleet fleet = lower.getFleet();
        if(grid[row][col] > 0){
            int id = grid[row][col];
            System.out.println(id);
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
            grid[row][col] = -1; // -1 should indicate miss
            return "MISS";
        }
    }

    public String use(int row, int col){
        String surfaceResult = helper(lower.getGrid(), row, col);
        String submergeResult = helper(lower.getSubmergedGrid(), row, col);

        if(surfaceResult == "HIT" || submergeResult == "HIT")
            return "HIT";
        else
            return "MISS";
    }
}
