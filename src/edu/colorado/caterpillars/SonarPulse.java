package edu.colorado.caterpillars;

import java.beans.PropertyChangeEvent;

public class SonarPulse extends Weapon{

    private int [][] grid;
    private boolean locked;
    private int sunkShipsReq;
    private int uses;
    private SunkData sunkData;

    public SonarPulse(LowerGrid lower, SunkData sunkData){
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


    public int [][] fire(int row, int col){
        int [][] result = new int [10][10];
        int startRow = row - 2;
        int endRow = row + 2;
        int startCol = col - 2;
        int endCol = col + 2;
        int topRow = startRow;

        if(startRow < 0){
            startRow = 0;
        }
        if(endRow > 9){
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

    public void use(int row, int col){
        if(locked){
            throw new RuntimeException("You need to sink one ship first!");
        }
        if(uses <= 0){
            throw new RuntimeException("You don't have any Sonar Pulses left!");
        }
        fire(row, col);
        uses--;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((int) evt.getNewValue() >= sunkShipsReq){
            locked = !locked;
            System.out.println(evt.getSource());
            sunkData.removeListener(this);
        }
    }
}
