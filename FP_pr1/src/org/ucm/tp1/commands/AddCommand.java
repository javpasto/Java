package org.ucm.tp1.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameObjects.Slayer;

public class AddCommand extends Command{
	
	private int x;
	private int y;

	public AddCommand(){
		super("", "", "", "[a]dd <x> <y>: add a slayer in position x, y", 3);
	}
	
	public AddCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 3);
		
		String[] aux = details.split(" ");
		this.x = Integer.parseInt(aux[0]);
		this.y = Integer.parseInt(aux[1]);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try{
			game.onBoardCommandPlayer(this.x, this.y);
			game.emptyCellCommand(this.x, this.y);
			game.quitarMonedasCommand(50);
		} catch (CommandExecuteException err){
			throw new CommandExecuteException(err.getMessage() + "\n[ERROR]: Failed to add slayer");
		}
		
		Slayer aux = new Slayer(this.x, this.y, game);
		game.addGameObject(aux);
		
		game.update();
		return true;
		
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("add") || commandWords[0].equalsIgnoreCase("a"))) {
			return new AddCommand("add", "a", commandWords[1] + " " + commandWords[2], "[a]dd <x> <y>: add a slayer in position x, y");			
		}
		return null;
	}

}
