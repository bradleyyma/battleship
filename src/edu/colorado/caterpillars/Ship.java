package edu.colorado.caterpillars;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {

    //Team caterpillars, pair 2 was here
    
    private String name;
    private int [] dimension;
    private int [][] shape;
    private int numHits;
    private boolean sunk;
    private int id;
    private int cid;
    private int cIndex;
    private boolean armored;


    public Ship(int id, String name, int [] dimension, int [][] shape, boolean armored) {
        this.id = id;
        this.name = name;
        this.dimension = dimension;
        this.shape = shape;
        numHits = 0;
        sunk = false;
        this.armored = armored;
    }


    //Team caterpillars, pair 1 is here.

    public String getName(){
        return name;
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

    public int[] getDimension() {return dimension;}

    public int[][] getShape() {return  shape;}

    public void setCID(int cid){
        this.cid = cid;
    }

    public void hit(){
        this.numHits++;
    }

    public void hitCaptainQuarters(){
        if(armored){
            armored = false;
        }else {
            sunk = true;
        }
    }


}
