package edu.colorado.caterpillars;
import edu.colorado.caterpillars.Fleet.Fleet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SunkData{
    private int numSunk;

    private PropertyChangeSupport support;

    private Fleet fleet;

    public SunkData(Fleet fleet){
        support = new PropertyChangeSupport(this);
        this.fleet = fleet;
    }

    public void addListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }

    public void removeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public void setNumSunk(int value){
        support.firePropertyChange("numSunk", this.numSunk, value);
        this.numSunk = value;
    }

    public void checkForUpdates(){
        int nSunk = fleet.getNumNonDummyShips() - fleet.getNumSurvivingShips();
        if(nSunk != numSunk){
            setNumSunk(nSunk);
        }
    }
}
