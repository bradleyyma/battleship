package edu.colorado.caterpillars;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpperGrid extends Grid implements PropertyChangeListener {

    private LowerGrid opponentLower;
    private AttackBehavior attackBehavior;
    private SunkData sunkData;

    public UpperGrid(LowerGrid lower, SunkData sunkData){
        opponentLower = lower;
        attackBehavior = new SendBasicAttack(opponentLower);
        this.sunkData = sunkData;
        sunkData.addListener(this);
    }

    public void setAttackBehavior(AttackBehavior ab){
        attackBehavior = ab;
    }

    public String sendAttack(int row, int col){
        String result = attackBehavior.attack(row, col);
        if(result == "MISS")
            grid[row][col] = -1;
        else // sunk and hit will both indicate 1
            grid[row][col] = 1;
        return result;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((int) evt.getNewValue() >= 1){
            AttackBehavior newAb = new SendLaserAttack();
            setAttackBehavior(newAb);
            sunkData.removeListener(this);
        }
    }
}
