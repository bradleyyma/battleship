package edu.colorado.caterpillars;

public class UpperGrid extends Grid{

    private LowerGrid opponentLower;

    public UpperGrid(LowerGrid lower){
        opponentLower = lower;
    }

    public String sendAttack(int row, int col){
        String result = opponentLower.receiveAttack(row, col);
        if(result == "MISS")
            grid[row][col] = -1;
        else // sunk and hit will both indicate 1
            grid[row][col] = 1;
        return result;
    }
}
