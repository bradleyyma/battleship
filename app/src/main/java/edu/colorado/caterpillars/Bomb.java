package edu.colorado.caterpillars;

public class Bomb extends Weapon{
    private int [][] grid;
    private Fleet fleet;
    public Bomb(LowerGrid lower){
        grid = lower.getGrid();
        fleet = lower.getFleet();
    }
    @Override
    public String use(int row, int col) {
        if(row < 0 || row >= 10 || col < 0 || col >= 10){
            throw new IndexOutOfBoundsException("Not a valid area to fire Bomb!");
        }
        String result = "MISS";
        int startRow = row - 1;
        int endRow = row + 1;
        int topRow = startRow;
        int startCol, endCol;
        if(startRow < 0){
            startRow = 0;
        }
        if(endRow >= 10){
            endRow = 9;
        }
        int width[] = {1, 3, 1};
        for(int i = startRow; i <= endRow; i ++){
            int halfRowWidth = (int)Math.floor(width[i-topRow]/2);
            startCol = col - halfRowWidth;
            endCol = col + halfRowWidth;
            if(startCol < 0){
                startCol = 0;
            }
            if(endCol > 9){
                endCol = 9;
            }
            for(int j = startCol; j <= endCol; j++){
                System.out.println(i + " " + j);
                if(grid[i][j] > 0){
                    int id = grid[i][j];
                    String tmpResult = fleet.hitShipById(id);
                    if(!tmpResult.equals("MISS")){
                        result = "HIT";
                        grid[i][j] = -id; // -ship.id represents a ship coord that has been hit (should be <= -2)
                    }
                    if(tmpResult.contains("SUNK") || tmpResult.equals("SURRENDER")){ // we hit unarmored CQ
                        Ship ship = fleet.getShipById(id);
                        int sid = ship.getID();
                        int h = grid.length;
                        int w = grid[0].length;
                        for(int ii = 0; ii < h; ii++){
                            for(int jj = 0; jj < w; jj++){
                                if(grid[ii][jj] == sid) {
                                    grid[ii][jj] = -sid;
                                }
                            }
                        }

                    }
                }
            }
        }
        return result;
    }


    @Override
    public void undoUse(int row, int col) {

    }
}
