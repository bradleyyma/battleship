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
        for (Ship ship : shipArray) {
            if (!ship.isSunk()) {
                survivingShips++;
            }
        }
        return survivingShips;
    }

    public Ship getShipById(int id){
        for (Ship ship : shipArray) {
            if (ship.id == id) {
                return ship;
            }
        }
        throw new IllegalArgumentException("ID not found in fleet");

    }

    public String hitShipById(int id){
        Ship ship = getShipById(id);
        int priorShips = getNumSurvivingShips();
        ship.hit();
        if(priorShips != getNumSurvivingShips()){
            return("SUNK");
        }else{
            return ("HIT");
        }//surrender


    }
}
