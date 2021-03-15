package edu.colorado.caterpillars;

import java.beans.PropertyChangeListener;

public abstract class Weapon implements PropertyChangeListener{
    protected int uses;
    protected boolean locked;
    public abstract String use(int row, int col);
}
