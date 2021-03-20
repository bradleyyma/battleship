package edu.colorado.caterpillars;

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
        undoStack.push(grid);
        if(result == "MISS")
            grid[row][col] = -1;
        else { // sunk and hit will both indicate 1
            sunkData.checkForUpdates();
            grid[row][col] = 1;
        }
        return result;
    }

    public void undoSendAttack(int row, int col){
        grid = undoStack.pop();
        attackBehavior.undoAttack(row, col);
        sunkData.checkForUpdates();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((int) evt.getNewValue() >= 1){
            AttackBehavior newAb = new SendLaserAttack(opponentLower);
            setAttackBehavior(newAb);
        }
        if((int) evt.getNewValue() == 0){
            AttackBehavior newAb = new SendBasicAttack(opponentLower);
            setAttackBehavior(newAb);
        }
    }
}
