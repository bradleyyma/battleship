package edu.colorado.caterpillars;

public class Player {
    private Player opponent;
    private LowerGrid lower;
    private UpperGrid upper;

    public Player(){
        lower = new LowerGrid();
    }

    public LowerGrid getLower(){
        return lower;
    }

    public UpperGrid getUpper(){
        return upper;
    }

    public void setOpponent(Player opponent, SunkData sunkData){
        this.opponent = opponent;
        sunkData.setFleet(opponent.getLower().getFleet());
        upper = new UpperGrid(this.opponent.getLower(), sunkData);
    }

    public void addShip(Ship ship, int row, int col, String dir){
        lower.addShip(ship, row, col, dir);
    }

    public String attack(int row, int col){
        return upper.sendAttack(row, col);
    }
}
