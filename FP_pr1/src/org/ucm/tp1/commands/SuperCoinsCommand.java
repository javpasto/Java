package org.ucm.tp1.commands;

import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends Command{
	
	private final int numCoins = 1000;

	public SuperCoinsCommand(){
		super("", "", "", "[c]oins: add 1000 coins", 1);
	}
	
	public SuperCoinsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help, 1);
	}

	//No se especifica que pase o no pase el turno
	@Override
	public boolean execute(Game game) {
		game.addNonTurnCoins(this.numCoins);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("coins") || commandWords[0].equalsIgnoreCase("c"))) {
			return new SuperCoinsCommand("coins", "c", "", "[c]oins: add 1000 coins");
		}
		return null;
	}

}
