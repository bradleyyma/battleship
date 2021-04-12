package edu.colorado.caterpillars.AttackBehavior;

public interface AttackBehavior {
    public String attack(int row, int col);
    public void undoAttack(int row, int col);
}
