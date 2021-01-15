package org.ucm.tp1.commands;

import org.ucm.tp1.logic.Game;

public class ExitCommand extends Command{
	
	public ExitCommand(){
		super("", "", "", "[e]xit: exit game", 1);
	}

	public ExitCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
	}

	@Override
	public boolean execute(Game game) {
		game.end();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("exit") || commandWords[0].equalsIgnoreCase("e"))) {
			return new ExitCommand("exit", "e", "", "[e]xit: exit game");
		}
		return null;
	}

}
