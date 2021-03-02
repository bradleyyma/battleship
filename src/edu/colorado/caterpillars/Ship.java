package edu.colorado.caterpillars;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {

    //Team caterpillars, pair 2 was here
    
    private String name;
    private int size;
    private int numHits;
    private boolean sunk;
    private int id;
    private int cid;

    public Ship(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
        numHits = 0;
        sunk = false;
    }


    //Team caterpillars, pair 1 is here.

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

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getCID(){
        return cid;
    }

    public void setCID(int cid){
        this.cid = cid;
    }


    public  void show()     {     // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }
}
