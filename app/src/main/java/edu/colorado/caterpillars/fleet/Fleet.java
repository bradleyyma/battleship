package edu.colorado.caterpillars.fleet;

import java.util.Arrays;

public class Fleet {

    private Ship[] shipArray;
    private int nextAvailId;
    private int[] sidArray;
    private int[] cidArray;

    public Fleet(){
        shipArray = new Ship[0];
        nextAvailId = 2;
        sidArray = new int[0];
        cidArray = new int[0];
    }



    public void addShip(Ship ship){
        // Append ship to shipArray
        shipArray = Arrays.copyOf(shipArray, shipArray.length + 1);
        ship.setID(nextAvailId);
        sidArray = Arrays.copyOf(sidArray, sidArray.length + 1);
        sidArray[sidArray.length - 1] = nextAvailId++;
        ship.setCID(nextAvailId);
        cidArray = Arrays.copyOf(cidArray, cidArray.length + 1);
        cidArray[cidArray.length - 1] = nextAvailId++;
        shipArray[shipArray.length - 1] = ship;
    }

    public int getNumSurvivingShips(){
        int survivingShips = 0;
        for (Ship ship : shipArray) {
            if (!ship.isSunk() && !ship.isDummy()) {
                survivingShips++;
            }
        }
        return survivingShips;
    }

    public int getNumNonDummyShips(){
        int count = 0;
        for(Ship ship : shipArray){
            if(!ship.isDummy())
                count ++;
        }
        return count;
    }

    public int getTotalNumShips(){
        return shipArray.length;
    }

    public Ship getShipById(int id){
        for (Ship ship : shipArray) {
            if (ship.getID() == id || ship.getCID() == id) {
                return ship;
            }
        }
        throw new IllegalArgumentException("ID not found in fleet");

    }

    public boolean inSidArray(int id){
        for (int index : sidArray) {
            if (index == id) {
                return true;
            }
        }
        return false;
    }

    public boolean inCidArray(int id){
        for (int index : cidArray) {
            if (index == id) {
                return true;
            }
        }
        return false;
    }

    public String hitShipById(int id){
        Ship ship = getShipById(id);
        int priorShips = getNumSurvivingShips();
        if(inSidArray(id)) {
            ship.hit();
            return("HIT");
        }else{
            ship.hitCaptainQuarters();
            if (priorShips != getNumSurvivingShips()) {
                if(getNumSurvivingShips() == 0){
                    return ("SURRENDER");
                }else {
                    return ("SUNK " + ship.getName());
                }
            } else {
                return ("MISS");
            }
        }


    }

    public void undoHitShipById(int id){
        Ship ship = getShipById(id);
        if(inSidArray(id)) {
            ship.undoHit();
        }else{
            ship.undoHitCQ();
        }
    }

    public void undoAddShip(){
        shipArray = Arrays.copyOf(shipArray, shipArray.length - 1);
        sidArray = Arrays.copyOf(sidArray, sidArray.length - 1);
        cidArray = Arrays.copyOf(cidArray, cidArray.length - 1);
        nextAvailId -= 2;
    }
}
