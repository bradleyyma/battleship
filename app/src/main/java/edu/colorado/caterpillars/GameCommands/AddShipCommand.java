package edu.colorado.caterpillars.GameCommands;

import edu.colorado.caterpillars.Fleet.Ship;
import edu.colorado.caterpillars.Main.Player;

public class AddShipCommand implements Command {
    private Ship ship;
    private int row;
    private int col;
    private String dir;
    private boolean submerge;
    private Player player;
    public AddShipCommand(Player player, Ship ship, int row, int col, String direction, boolean submerge){
        this.ship = ship;
        this.row = row;
        this.col = col;
        this.dir = direction;
        this.submerge = submerge;
        this.player = player;
    }

    @Override
    public void execute() {
        player.getLower().addGridsToHistory();
        player.getLower().addShip(ship,row,col,dir,submerge);
    }

    @Override
    public void undo() {
        player.getLower().undoAddShip();
    }
}
