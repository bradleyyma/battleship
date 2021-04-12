package edu.colorado.caterpillars;
import java.util.Stack;

public class Game{
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Player waitingPlayer;
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    private Command swapPlayers;
    private static Game instance;


    private Game(){
        player1 = new Player();
        player2 = new Player();
        swapPlayers = new SwitchPlayersCommand(this);

        instantiateMoveCommands();
        instantiateAttackCommands();

        player1.setOpponent(player2);
        player2.setOpponent(player1);
        activePlayer = player1;
        waitingPlayer = player2;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public static synchronized Game getInstance() {
        if (instance == null){
            instance = new Game();
        }
        return instance;
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
    private void instantiateAttackCommands(){
        for(int row = 0; row < 10; row ++){
            for(int col = 0; col < 10; col++){
                Command p1attack = new AttackCommand(player1, row, col);
                Command p2attack = new AttackCommand(player2, row, col);
                player1.setAttackCommand(row, col, p1attack);
                player2.setAttackCommand(row, col, p2attack);
            }
        }
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
        return player1.getLower().getFleet().getNumSurvivingShips() != 0 &&
                player2.getLower().getFleet().getNumSurvivingShips() != 0;
    }

    public void attack(int row, int col){
        Command command = activePlayer.getAttackCommand(row, col);
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void move(String dir){
        Command command = activePlayer.getMoveFleetCommand(dir);
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo(){
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
    }

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
        redoStack.clear();

    }

    public void addShip(Ship ship, int row, int col, String direction, boolean submerge){
        Command command = new AddShipCommand(activePlayer,ship,row,col,direction,submerge);
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public static void endGame(){
        instance = null;
    }


}
