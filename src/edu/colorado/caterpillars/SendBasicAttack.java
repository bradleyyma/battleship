package edu.colorado.caterpillars;

public class SendBasicAttack implements AttackBehavior{
    private LowerGrid opponentLower;
    public SendBasicAttack(LowerGrid lower){
        opponentLower = lower;
    }
    public String attack(int row, int col){
        BasicAttack ba = new BasicAttack(opponentLower);
        String result = opponentLower.receiveAttack(ba, row, col);
        return result;
    }
}
