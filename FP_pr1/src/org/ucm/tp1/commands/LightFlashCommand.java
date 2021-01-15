package org.ucm.tp1.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends Command{
	
	public LightFlashCommand(){
		super("", "", "", "[l]ight : elimina a todos los vampiros del tablero", 1);
	}

	public LightFlashCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		try{
			game.quitarMonedasCommand(50);
		} catch (CommandExecuteException err){
			throw new CommandExecuteException(err.getMessage() + "\n[ERROR]: Failed to ligtht flash");
		}
		game.lightFlash();
		game.update();

		
		return true;
		
	}

	@Override
	public Command parse(String[] commandWords) {
		if (commandWords[0].isEmpty() || ((commandWords[0].equalsIgnoreCase("l")|| commandWords[0].equalsIgnoreCase("light")))){
			return new LightFlashCommand("light", "l", "", "[l]ight : elimina a todos los vampiros del tablero");
		}
		return null;
	}

}
