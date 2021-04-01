package edu.colorado.caterpillars;

public interface AttackBehavior {
    public String attack(int row, int col);
    public void undoAttack(int row, int col);
}
