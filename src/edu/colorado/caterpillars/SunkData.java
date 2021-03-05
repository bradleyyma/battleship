package edu.colorado.caterpillars;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class SunkData{
    private int numSunk;

    private PropertyChangeSupport support;

    private Fleet fleet;

    public SunkData(){
        support = new PropertyChangeSupport(this);
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

    public void setFleet(Fleet fleet){
        this.fleet = fleet;
    }

    public void checkForUpdates(){
        int nSunk = fleet.getTotalNumShips() - fleet.getNumSurvivingShips();
        if(nSunk != numSunk){
            setNumSunk(nSunk);
        }
    }
}
