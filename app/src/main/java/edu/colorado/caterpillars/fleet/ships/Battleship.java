package edu.colorado.caterpillars.fleet.ships;

import edu.colorado.caterpillars.fleet.Ship;

public class Battleship extends Ship {
    public Battleship(){
        super(0, "Battleship", new int [][]{{1, 1, 2, 1}},true,false, false);
    }
}
