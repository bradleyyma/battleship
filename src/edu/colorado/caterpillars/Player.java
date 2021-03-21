package edu.colorado.caterpillars;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private Player opponent;
    private LowerGrid lower;
    private UpperGrid upper;
    private Map<String, Command> moveFleetCommands;

    public Player(){
        lower = new LowerGrid();
        moveFleetCommands = new HashMap<>();
    }

    public LowerGrid getLower(){
        return lower;
    }

    public UpperGrid getUpper(){
        return upper;
    }

    public void setOpponent(Player opponent){
        this.opponent = opponent;
        LowerGrid opponentLower = opponent.getLower();
        SunkData sunkData = new SunkData(opponentLower.getFleet());
        upper = new UpperGrid(opponentLower, sunkData);
    }

    public void addShip(Ship ship, int row, int col, String dir,boolean submerge){
        lower.addShip(ship, row, col, dir,submerge);
    }

    public String attack(int row, int col){
        return upper.sendAttack(row, col);
    }

    public void undoAttack(int row, int col){
        upper.undoSendAttack(row, col);
    }

    public Command getMoveFleetCommand(String dir){
        return moveFleetCommands.get(dir);
    }

    public void setMoveFleetCommand(String dir, Command moveCommand){
        moveFleetCommands.put(dir, moveCommand);
    }
}
