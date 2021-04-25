package edu.colorado.caterpillars.grid;

import edu.colorado.caterpillars.*;
import edu.colorado.caterpillars.attackBehavior.AttackBehavior;
import edu.colorado.caterpillars.attackBehavior.SendBasicAttack;
import edu.colorado.caterpillars.attackBehavior.SendLaserAttack;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;

public class UpperGrid extends Grid implements PropertyChangeListener {

    private LowerGrid opponentLower;
    private AttackBehavior attackBehavior;
    private SunkData sunkData;
    private Stack<int[][]> undoStack;

    public UpperGrid(LowerGrid lower, SunkData sunkData){
        opponentLower = lower;
        attackBehavior = new SendBasicAttack(opponentLower);
        this.sunkData = sunkData;
        sunkData.addListener(this);
        undoStack = new Stack<>();
    }

    public void setAttackBehavior(AttackBehavior ab){
        attackBehavior = ab;
    }

    public String sendAttack(int row, int col){
        String result = attackBehavior.attack(row, col);
        addGridsToHistory();
        if(result == "MISS")
            grid[row][col] = -1;
        else { // sunk and hit will both indicate 1
            sunkData.checkForUpdates();
            grid[row][col] = 1;
        }
        return result;
    }

    public void undoSendAttack(int row, int col){
        undoGrids();
        attackBehavior.undoAttack(row, col);
        sunkData.checkForUpdates();
    }

    public void addGridsToHistory(){
        int [][] oldGrid = new int [ROWS][COLS];
        for(int i = 0; i < ROWS; i++){
            System.arraycopy(grid[i], 0, oldGrid[i], 0, COLS);
        }
        undoStack.push(oldGrid);
    }

    public void undoGrids(){
        grid = undoStack.pop();
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((int) evt.getOldValue() == 0 && (int) evt.getNewValue() == 1){
            AttackBehavior newAb = new SendLaserAttack(opponentLower);
            setAttackBehavior(newAb);
        }
        if((int) evt.getOldValue() == 1 && (int) evt.getNewValue() == 0){
            AttackBehavior newAb = new SendBasicAttack(opponentLower);
            setAttackBehavior(newAb);
        }
    }
}
