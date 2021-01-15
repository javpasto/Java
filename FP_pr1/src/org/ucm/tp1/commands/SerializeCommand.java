package org.ucm.tp1.commands;

import org.ucm.tp1.logic.Game;

public class SerializeCommand extends Command{

	public SerializeCommand(){
		super("", "", "", "seriali[z]e: serialize program state", 1);
	}
	
	public SerializeCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
	}

	@Override
	public boolean execute(Game game) {

		System.out.println(game.serialize());
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("serialize") || commandWords[0].equalsIgnoreCase("z"))) {
			return new SerializeCommand("serialize", "z", "", "seriali[z]e: serialize program state");
		}
		return null;
	}
	
}
