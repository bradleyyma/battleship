package edu.colorado.caterpillars.AttackBehavior;

import edu.colorado.caterpillars.Grid.LowerGrid;
import edu.colorado.caterpillars.Weapons.BasicAttack;

public class SendBasicAttack implements AttackBehavior {
    private LowerGrid opponentLower;
    public SendBasicAttack(LowerGrid lower){
        opponentLower = lower;
    }
    public String attack(int row, int col){
        BasicAttack ba = new BasicAttack(opponentLower);
        String result = opponentLower.receiveAttack(ba, row, col);
        return result;
    }

    public void undoAttack(int row, int col){
        BasicAttack ba = new BasicAttack(opponentLower);
        opponentLower.undoReceiveAttack(ba, row, col);
    }

}