package edu.colorado.caterpillars.gameCommands;

import edu.colorado.caterpillars.main.Game;

public class SwitchPlayersCommand implements Command {

    private Game game;

    public SwitchPlayersCommand(Game game){
        this.game = game;
    }

    @Override
    public void execute() {
        game.swapPlayers();
    }

    @Override
    public void undo() {
        game.swapPlayers();
    }


}
