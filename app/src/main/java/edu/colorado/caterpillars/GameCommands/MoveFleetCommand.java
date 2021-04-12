package edu.colorado.caterpillars.GameCommands;

import edu.colorado.caterpillars.Main.Player;

public class MoveFleetCommand implements Command {

    private Player player;
    private String dir;
    public MoveFleetCommand(Player player,String dir){
        this.player = player;
        this.dir = dir;
    }
    @Override
    public void execute() {
        player.getLower().moveFleet(dir);
    }

    @Override
    public void undo() {
        switch (dir) {
            case "W":
                player.getLower().moveFleet("E");
                break;
            case "E":
                player.getLower().moveFleet("W");
                break;
            case "N":
                player.getLower().moveFleet("S");
                break;
            case "S":
                player.getLower().moveFleet("N");
                break;
        }

    }
}
