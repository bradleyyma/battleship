package edu.colorado.caterpillars.attackBehavior;

import edu.colorado.caterpillars.grid.LowerGrid;
import edu.colorado.caterpillars.weapons.SpaceLaser;

public class SendLaserAttack implements AttackBehavior {
    private LowerGrid opponentLower;
    public SendLaserAttack(LowerGrid lower){
        opponentLower = lower;
    }
    @Override
    public String attack(int row, int col) {
        SpaceLaser sl = new SpaceLaser(opponentLower);
        String result = opponentLower.receiveAttack(sl, row, col);
        return result;
    }

    @Override
    public void undoAttack(int row, int col) {
        SpaceLaser sl = new SpaceLaser(opponentLower);
        opponentLower.undoReceiveAttack(sl, row, col);
    }
}
