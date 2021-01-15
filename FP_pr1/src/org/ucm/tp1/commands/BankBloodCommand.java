package org.ucm.tp1.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameObjects.BankBlood;

public class BankBloodCommand extends Command{

	private int x;
	private int y;
	private int z;

	public BankBloodCommand(){
		super("", "", "", "[b]ank <x> <y> <z>: add a bank_blood in position x, y with z blood", 4);
	}
	
	public BankBloodCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 4);
		
		String[] aux = details.split(" ");
		this.x = Integer.parseInt(aux[0]);
		this.y = Integer.parseInt(aux[1]);
		this.z = Integer.parseInt(aux[2]);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		
		try{
		game.onBoardCommandPlayer(this.x, this.y);
		game.emptyCellCommand(this.x, this.y);
		game.quitarMonedasCommand(this.z);
		} catch (CommandExecuteException err){
			throw new CommandExecuteException(err.getMessage() + "\n[ERROR]: Failed to add bank");
		}
		
		BankBlood aux = new BankBlood(this.x, this.y, game, this.z);
		game.addGameObject(aux);		
		game.update();
		return true;
		
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("bank") || commandWords[0].equalsIgnoreCase("b"))) {
			return new BankBloodCommand("add", "a", commandWords[1] + " " + commandWords[2] + " " + commandWords[3], "[b]ank <x> <y> <z>: add a bank_blood in position x, y with z blood");			
		}
		return null;
	}

}
