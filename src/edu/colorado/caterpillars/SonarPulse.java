package edu.colorado.caterpillars;

public class SonarPulse extends Weapon{

    private int [][] grid;

    public SonarPulse(LowerGrid lower){
        grid = lower.getGrid();
    }

    public int [][] fire(int row, int col){
        int [][] result = new int [10][10];
        int startRow = row - 2;
        int endRow = row + 2;
        int startCol = col - 2;
        int endCol = col + 2;
        int width[] = {1, 3, 5, 3, 1};
        for(int i = startRow; i <= endRow; i ++){
            int halfRowWidth = (int)Math.floor(width[i-startRow]/2);
            for(int j = col - halfRowWidth; j <= col + halfRowWidth; j++){
                if(grid[i][j] == -1 || grid[i][j] == 0){ // Nothing detected (gray in req image)
                    result[i][j] = -1;
                }
                else{
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }
}
