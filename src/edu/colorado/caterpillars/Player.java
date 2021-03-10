package edu.colorado.caterpillars;

public class Player {
    private Player opponent;
    private LowerGrid lower;

    public Player(){
        lower = new LowerGrid();
    }

    public int [][] getLowerGrid() {
        return lower.getGrid();
    }

    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    public void addShip(Ship ship, int row, int col, String dir){
        lower.addShip(ship, row, col, dir);
    }
}
