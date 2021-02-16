package edu.colorado.caterpillars;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {

    //Team caterpillars, pair 2 was here
    
    private String name;
    private int size;
    private int numHits;
    private boolean sunk;
    public int id;

    public Ship(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
        numHits = 0;
        sunk = false;
    }


    //Team caterpillars, pair 1 is here.

    // TODO: create appropriate getter and setter methods
    // TODO: Understand encapsulation
    // TODO: Understand what these todo comments mean


    public String getName(){
        return name;
    }
    public int getSize(){
        return this.size;
    }
    public void hit(){
        this.numHits++;
        if(this.numHits == this.size){
            this.sunk = true;
        }
    }
    public int getNumHits(){
        return this.numHits;
    }
    public boolean isSunk(){
        return this.sunk;
    }


    public  void show()     {     // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }
}
