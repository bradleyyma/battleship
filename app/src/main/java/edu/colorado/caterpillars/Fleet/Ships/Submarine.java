package edu.colorado.caterpillars.Fleet.Ships;

import edu.colorado.caterpillars.Fleet.Ship;

public class Submarine extends Ship {
    public Submarine(){
        super(0, "Submarine", new int[][]{{0,0,1,0},{1,1,1,2}},true,true, false);
    }
}
