package edu.colorado.caterpillars;

import java.beans.PropertyChangeListener;

public abstract class Weapon{
    protected int uses;
    protected boolean locked;
    public abstract String use(int row, int col);
}
