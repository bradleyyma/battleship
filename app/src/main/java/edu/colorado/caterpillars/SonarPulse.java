package edu.colorado.caterpillars;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SonarPulse extends Weapon implements PropertyChangeListener {

    private int [][] grid;
    private int sunkShipsReq;
    private SunkData sunkData;

    public SonarPulse(LowerGrid lower, SunkData sunkData){
        weaponName = "Sonar Pulse";
        grid = lower.getGrid();
        locked = true;
        sunkShipsReq = 1;
        uses = 2;
        this.sunkData = sunkData;
        sunkData.addListener(this);
    }

    public boolean isLocked(){
        return locked;
    }


    public int [][] fireHelper(int row, int col){
        if(row < 0 || row >= 10 || col < 0 || col >= 10){
            throw new IndexOutOfBoundsException("Not a valid area to fire SonarPulse!");
        }
        int [][] result = new int [10][10];
        int startRow = row - 2;
        int endRow = row + 2;
        int topRow = startRow;
        int startCol, endCol;
        if(startRow < 0){
            startRow = 0;
        }
        if(endRow >= 10){
            endRow = 9;
        }
        int width[] = {1, 3, 5, 3, 1};
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
                if(grid[i][j] == -1 || grid[i][j] == 0){ // Nothing detected (gray in req image)
                    result[i][j] = -1;
                }
                else{ // something detected, (black in req image)
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }

    public String fire(int row, int col){
        fireHelper(row, col);
        return weaponName + " Used";
    }

    @Override
    public void lockedErrorHook(){
        throw new RuntimeException("You need to sink " + sunkShipsReq + " ship first, before using a " + weaponName + "!");
    }

    @Override
    public void undoUse(int row, int col) {
        if(uses < 2)
            uses++;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((int) evt.getNewValue() >= sunkShipsReq){
            locked = !locked;
            sunkData.removeListener(this);
        }
    }
}
