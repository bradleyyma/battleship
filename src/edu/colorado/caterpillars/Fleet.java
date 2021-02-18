package edu.colorado.caterpillars;

import java.util.Arrays;

public class Fleet {

    private Ship[] shipArray = new Ship[0];

    public void addShip(Ship ship){
        // Append ship to shipArray
        shipArray = Arrays.copyOf(shipArray, shipArray.length + 1);
        shipArray[shipArray.length - 1] = ship;
    }

    public int getNumSurvivingShips(){
        int survivingShips = 0;
        for(int i = 0; i < shipArray.length; i++){
            if(!shipArray[i].isSunk()){
                survivingShips++;
            }
        }
        return survivingShips;
    }

    public Ship getShipById(int id){
        for(int i = 0; i < shipArray.length; i++) {
            if (shipArray[i].id == id) {
                return shipArray[i];
            }
        }
        throw new IllegalArgumentException("ID not found in fleet");

    }
}
