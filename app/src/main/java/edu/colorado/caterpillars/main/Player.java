package edu.colorado.caterpillars.main;

import edu.colorado.caterpillars.fleet.Ship;
import edu.colorado.caterpillars.gameCommands.Command;
import edu.colorado.caterpillars.grid.UpperGrid;
import edu.colorado.caterpillars.grid.LowerGrid;
import edu.colorado.caterpillars.SunkData;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private Player opponent;
    private LowerGrid lower;
    private UpperGrid upper;
    private Map<String, Command> moveFleetCommands;
    private Command [][] attackCommands;
    private String name;

    public Player(){
        lower = new LowerGrid();
        moveFleetCommands = new HashMap<>();
        attackCommands = new Command[10][10];
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

    public void addShip(Ship ship, int row, int col, String dir, boolean submerge){
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

    public Command getAttackCommand(int row, int col){
        return attackCommands[row][col];
    }
    public void setAttackCommand(int row, int col, Command attackCommand){
        attackCommands[row][col] = attackCommand;
    }
    public void setName(String name){this.name = name;}
    public String getName(){ return name;}
}
