package edu.colorado.caterpillars;

public class ShipShape {
    private int [][] shape;
    private int length;
    private int width;

    public ShipShape(int [][] shape){
        this.shape = shape;
        length = shape[0].length;
        width = shape.length;
    }

    public int getWidth(){
        return width;
    }

    public int getLength(){
        return length;
    }

    public int [][] getShape(){
        return shape;
    }
}
