package edu.colorado.caterpillars.Fleet.Ships;

import edu.colorado.caterpillars.Fleet.Ship;

public class Battleship extends Ship {
    public Battleship(){
        super(0, "Battleship", new int [][]{{1, 1, 2, 1}},true,false, false);
    }
}
