package edu.colorado.caterpillars;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Bomb extends Weapon implements PropertyChangeListener{
    private LowerGrid lower;
    private UpperGrid upper;
    private int sunkShipsReq;

    public Bomb(LowerGrid lower, UpperGrid upper, SunkData sunkData){
        weaponName = "Bomb";
        this.lower = lower;
        locked = true;
        this.upper = upper;
        sunkShipsReq = 2;
        uses = 1;
        sunkData.addListener(this);
    }

    @Override
    public String fire(int row, int col) {
        lower.addGridsToHistory();
        upper.addGridsToHistory();
        uses--;
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
        int [][] grid = lower.getGrid();
        Fleet fleet = lower.getFleet();
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
                if(grid[i][j] > 0){
                    int id = grid[i][j];
                    String tmpResult = fleet.hitShipById(id);
                    if(!tmpResult.equals("MISS")){
                        if(result.equals("MISS"))
                            result = "HIT";
                        grid[i][j] = -id; // -ship.id represents a ship coord that has been hit (should be <= -2)
                        upper.getGrid()[i][j] = 1;
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
                        if(result.equals("HIT") || result.equals("MISS")) // if the current result is only a hit or miss, then it can be replaced
                            result = tmpResult;
                        else if(tmpResult.contains("SUNK") && !result.contains(tmpResult)) // add additional sunk ships to current sunk return
                            result = result + ", " + tmpResult;
                        else if(tmpResult.equals("SURRENDER")) // if result is surrender, then replace any current result
                            result = tmpResult;
                    }
                }
            }
        }
        return result;
    }


    @Override
    public void undoUse(int row, int col) {
        uses++;
        lower.undoGrids();
        upper.undoGrids();
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
        int [][] grid = lower.getGrid();
        Fleet fleet = lower.getFleet();
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
                if(grid[i][j] > 0){
                    int id = grid[i][j];
                    fleet.undoHitShipById(id);
                }
            }
        }
    }




    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((int) evt.getNewValue() >= sunkShipsReq){
            locked = false;
        }
        if((int) evt.getNewValue() < sunkShipsReq)
            locked = true;
    }
}
