package edu.colorado.caterpillars;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {

    //Team caterpillars, pair 2 was here
    
    private String name;
    private ShipShape shape;
    private int numHits;
    private boolean sunk;
    private int id;
    private int cid;
    private boolean armored;
    private boolean canSubmerge;


    public Ship(int id, String name, int [][] shape, boolean armored, boolean canSubmerge) {
        this.id = id;
        this.name = name;
        this.shape = new ShipShape(shape);
        this.canSubmerge = canSubmerge;
        numHits = 0;
        sunk = false;
        this.armored = armored;
    }


    //Team caterpillars, pair 1 is here.

    public String getName(){
        return name;
    }

    public int getNumHits(){
        return numHits;
    }
    public boolean isSunk(){
        return sunk;
    }
    public boolean canSubmerge() {return canSubmerge;}

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getCID(){
        return cid;
    }

    public int[][] getShape() {return shape.getShape();}

    public int getLength() { return shape.getLength(); }

    public int getWidth() { return shape.getWidth(); }

    public void setCID(int cid){
        this.cid = cid;
    }

    public void hit(){
        this.numHits++;
    }

    public void undoHit(){
        this.numHits--;
    }

    public void hitCaptainQuarters(){
        if(armored){
            armored = false;
        }else {
            sunk = true;
        }
    }

    public void undoHitCQ(){
        if(sunk){
            sunk = false;
        }
        else{
            armored = true;
        }
    }



}
