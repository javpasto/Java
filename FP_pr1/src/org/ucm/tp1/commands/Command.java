package org.ucm.tp1.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.logic.Game;

public abstract class Command {
	
	protected final String name;
	protected final String shortcut;
	private final String details ;
	private final String help;
	private final int numArguments;
	protected static final String incorrectNumberOfArgsMsg = "Incorrect number of arguments";
	protected static final String incorrectArgsMsg = "Incorrect arguments format";
	
	
	public Command(String name, String shortcut, String details, String help, int numArguments){
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
		this.numArguments = numArguments;
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}
	
	protected Command numArgumentsCommand(String[] words, Command com) throws CommandParseException {
		if (words.length != com.numArguments) {
			throw new CommandParseException("Command " + com.name + ": " + incorrectNumberOfArgsMsg);
		}
		return com;
	}

	
	public String helpText(){
		return this.help;
	}

	public String getDetails() {
		return this.details;
	}
}
