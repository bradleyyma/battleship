package edu.colorado.caterpillars;
import java.util.Stack;

public class Game{
    private Player player1;
    private Player player2;
    private boolean isRunning;
    private Player activePlayer;
    private Player waitingPlayer;
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    private Command swapPlayers;

    public Game(){
        player1 = new Player();
        player2 = new Player();
        swapPlayers = new SwitchPlayersCommand(this);

        instantiateMoveCommands();

        player1.setOpponent(player2);
        player2.setOpponent(player1);
        isRunning = true;
        activePlayer = player1;
        waitingPlayer = player2;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    private void instantiateMoveCommands(){


        Command moveNorth1 = new MoveFleetCommand(player1,"N");
        Command moveSouth1 = new MoveFleetCommand(player1,"S");
        Command moveEast1 = new MoveFleetCommand(player1,"E");
        Command moveWest1 = new MoveFleetCommand(player1,"W");

        Command moveNorth2 = new MoveFleetCommand(player2,"N");
        Command moveSouth2 = new MoveFleetCommand(player2,"S");
        Command moveEast2 = new MoveFleetCommand(player2,"E");
        Command moveWest2 = new MoveFleetCommand(player2,"W");

        player1.setMoveFleetCommand("N", moveNorth1);
        player1.setMoveFleetCommand("S", moveSouth1);
        player1.setMoveFleetCommand("E", moveEast1);
        player1.setMoveFleetCommand("W", moveWest1);

        player2.setMoveFleetCommand("N", moveNorth2);
        player2.setMoveFleetCommand("S", moveSouth2);
        player2.setMoveFleetCommand("E", moveEast2);
        player2.setMoveFleetCommand("W", moveWest2);
    }

    public Player getPlayer(int playerNum){
        if(playerNum == 1)
            return player1;
        else if(playerNum == 2)
            return player2;
        else
            throw new IllegalArgumentException("Invalid Player Number!");
    }

    public Player getActivePlayer(){
        return activePlayer;
    }

    public Player getWaitingPlayer(){
        return waitingPlayer;
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void attack(int row, int col){
        activePlayer.attack(row, col);
        if(waitingPlayer.getLower().getFleet().getNumSurvivingShips() <= 0){
            isRunning = false;
        }
    }

    public void move(String dir){
        Command command = activePlayer.getMoveFleetCommand(dir);
        command.execute();
        undoStack.push(command);
        redoStack = new Stack<>();
    }

    public void undo(){
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
    };

    public void redo(){
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }

    public void swapPlayers(){
        Player temp = activePlayer;
        activePlayer = waitingPlayer;
        waitingPlayer = temp;
    }

    public void endTurn(){
        Command command = swapPlayers;
        command.execute();
        undoStack.push(command);
        redoStack = new Stack<>();

    }

    public void addShip(Ship ship, int row, int col, String direction, boolean submerge){
        Command command = new AddShipCommand(activePlayer,ship,row,col,direction,submerge);
        command.execute();
        undoStack.push(command);
        redoStack = new Stack<>();
    }


}
