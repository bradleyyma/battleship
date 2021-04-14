package edu.colorado.caterpillars.fleet.ships;

import edu.colorado.caterpillars.fleet.Ship;

public class DummyShip extends Ship {
    public DummyShip(){
        super(0, "Dummy", new int[][]{{1}},false,false, true);
    }
}
