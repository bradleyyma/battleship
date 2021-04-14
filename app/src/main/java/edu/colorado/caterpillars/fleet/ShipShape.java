package edu.colorado.caterpillars.fleet;

public class ShipShape {
    private int [][] shape;
    private int length;
    private int width;
    final static public int id = 1;
    final static public int cid = 3;

    public ShipShape(int [][] shape){
        this.shape = shape;
        length = shape[0].length;
        width = shape.length;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                if(shape[i][j] == 1)
                    shape[i][j] = id;
                else if(shape[i][j] == 2)
                    shape[i][j] = cid;
            }
        }
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
