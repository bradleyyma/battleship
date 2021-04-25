package edu.colorado.caterpillars.attackBehavior;

public interface AttackBehavior {
    String attack(int row, int col);
    void undoAttack(int row, int col);
}
