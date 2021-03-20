package edu.colorado.caterpillars;

public class BasicAttack extends Weapon{
    private LowerGrid lower;
    private int [][] grid;
    private Fleet fleet;
    public BasicAttack(LowerGrid lower){
        this.lower = lower;
        grid = lower.getGrid();
        fleet = lower.getFleet();
    }

    public String use(int row, int col){
        lower.addGridToHistory();
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
            grid[row][col] = -1; // -1 should indicate miss
            return "MISS";
        }
    }

    @Override
    public void undoUse(int row, int col) {
        lower.undoGrids();
        grid = lower.getGrid();
        if(grid[row][col] > 0){
            int id = grid[row][col];
            fleet.undoHitShipById(id);
        }
    }
}
