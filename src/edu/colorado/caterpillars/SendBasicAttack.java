package edu.colorado.caterpillars;

public class SendBasicAttack implements AttackBehavior{
    private LowerGrid opponentLower;
    public SendBasicAttack(LowerGrid lower){
        opponentLower = lower;
    }
    public String attack(int row, int col){
        String result = opponentLower.receiveAttack(row, col);
        return result;
    }
}
