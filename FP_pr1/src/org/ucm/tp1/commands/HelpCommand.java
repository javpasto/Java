package org.ucm.tp1.commands;

import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command{

	public HelpCommand(){
		super("", "", "", "[h]elp: show this help", 1);
	}
	
	public HelpCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
	}

	@Override
	public boolean execute(Game game) {
		CommandGenerator.helpMessage();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("help") || commandWords[0].equalsIgnoreCase("h"))) {
			return new HelpCommand("help", "h", "", "[h]elp: show this help");
		}
		return null;
	}
	
	/*
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	*/

}
