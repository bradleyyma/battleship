package edu.colorado.caterpillars;

public class SwitchPlayersCommand implements Command{

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
