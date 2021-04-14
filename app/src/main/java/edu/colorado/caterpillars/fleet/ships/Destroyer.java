package edu.colorado.caterpillars.fleet.ships;

import edu.colorado.caterpillars.fleet.Ship;

public class Destroyer extends Ship {
    public Destroyer(){
        super(0, "Destroyer", new int[][]{{1,2,1}},true,false, false);
    }
}
