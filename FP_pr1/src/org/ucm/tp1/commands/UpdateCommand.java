package org.ucm.tp1.commands;

import org.ucm.tp1.logic.Game;

public class UpdateCommand extends Command{

	//For CommandGenerator
	public UpdateCommand(){
		super("","","","[n]one | []: update", 1);
	}
	
	public UpdateCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].isEmpty() || ((commandWords[0].equalsIgnoreCase("n")|| commandWords[0].equalsIgnoreCase("none")))){
			return new UpdateCommand("none", "n", "", "[n]one | []: update");
		}
		return null;
	}

}

