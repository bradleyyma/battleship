package edu.colorado.caterpillars;

public abstract class Weapon{
    protected int uses;
    protected boolean locked;
    public abstract String use(int row, int col);
    public abstract void undoUse(int row, int col);
}
