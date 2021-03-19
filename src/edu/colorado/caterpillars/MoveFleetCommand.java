package edu.colorado.caterpillars;

public class MoveFleetCommand implements Command{

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
        if(dir == "W"){
            player.getLower().moveFleet("E");
        }else if(dir == "E"){
            player.getLower().moveFleet("W");
        }else if(dir == "N"){
            player.getLower().moveFleet("S");
        }else if(dir == "S"){
            player.getLower().moveFleet("N");
        }

    }
}
