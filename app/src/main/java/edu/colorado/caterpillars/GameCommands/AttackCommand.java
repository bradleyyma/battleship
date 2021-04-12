package edu.colorado.caterpillars.GameCommands;

import edu.colorado.caterpillars.GameCommands.Command;
import edu.colorado.caterpillars.Main.Player;

public class AttackCommand implements Command {
    private int row;
    private int col;
    private Player player;


    public AttackCommand(Player player, int row, int col){
        this.row = row;
        this.col = col;
        this.player = player;
    }
    @Override
    public void execute() {
        player.attack(row, col);
    }

    @Override
    public void undo() {
        player.undoAttack(row, col);
    }
}
