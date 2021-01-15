package org.ucm.tp1.commands;

import org.ucm.tp1.logic.Game;

public class ResetCommand extends Command{
	
	//For CommandGenerator
	public ResetCommand() {
		super("","","","[r]eset: reset game", 1);
	}

	public ResetCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("reset") || commandWords[0].equalsIgnoreCase("r"))) {
			return new ResetCommand("reset","r","","[r]eset: reset game");
		}
		return null;
	}

}
