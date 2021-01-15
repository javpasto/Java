package org.ucm.tp1.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.Game;

public class GarlicPushCommand extends Command{
	
	public GarlicPushCommand(){
		super("","","","[g]arlic | [g]: push vampires 1 tile", 1);
	}

	public GarlicPushCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		try{
			game.quitarMonedasCommand(10);
		} catch (CommandExecuteException err){
			throw new CommandExecuteException(err.getMessage() + "\n[ERROR]: Failed to garlic push");
		}
		
		game.garlicPush();
		game.update();
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("garlic") || commandWords[0].equalsIgnoreCase("g"))) {
			return new GarlicPushCommand("garlic","g","","[g]arlic: push vampires 1 tile");
		}
		return null;
	}

}
