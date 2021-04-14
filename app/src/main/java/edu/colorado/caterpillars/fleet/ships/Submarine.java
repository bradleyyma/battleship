package edu.colorado.caterpillars.fleet.ships;

import edu.colorado.caterpillars.fleet.Ship;

public class Submarine extends Ship {
    public Submarine(){
        super(0, "Submarine", new int[][]{{0,0,1,0},{1,1,1,2}},true,true, false);
    }
}
