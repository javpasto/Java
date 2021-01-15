package org.ucm.tp1.commands;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.CommandParseException;
import org.ucm.tp1.exceptions.DraculaIsAliveException;
import org.ucm.tp1.exceptions.NoMoreVampiresException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameObjects.Dracula;
import org.ucm.tp1.logic.gameObjects.ExplosiveVampire;
import org.ucm.tp1.logic.gameObjects.Vampire;

public class AddVampireCommand extends Command{

	private int x;
	private int y;
	private String type;
	
	public AddVampireCommand(){
		super("", "", "", "[v]ampire <type> <x> <y> : Add vampire of type selected on position x, y. Possible types are 'd', 'n'|'' and 'e'", 4);
	}
	
	public AddVampireCommand(String name, String shortcut, String details, String help, String type) {
		super(name, shortcut, details, help, 4);
		
		String[] aux = this.getDetails().split(" ");
		
		this.x = Integer.parseInt(aux[0]);
		this.y = Integer.parseInt(aux[1]);
		
		this.type = type;
	}
	
	public AddVampireCommand(String name, String shortcut, String details, String help, String type, int numArguments) {
		super(name, shortcut, details, help, numArguments);
		
		String[] aux = this.getDetails().split(" ");
		
		this.x = Integer.parseInt(aux[0]);
		this.y = Integer.parseInt(aux[1]);
		
		this.type = type;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		
		try{
			if (game.vampiresLeft() == 0) throw new NoMoreVampiresException("No more vampires left to spawn");
		
			game.onBoardCommand(x, y);
			game.emptyCellCommand(x, y);
	
			
			boolean rtr = false;
			
			if (type.equalsIgnoreCase("n") || type.equalsIgnoreCase(""))	rtr = game.addGameObject(new Vampire(x, y, game));
			else if (type.equalsIgnoreCase("d")){
				if (game.draculaAlive())throw new DraculaIsAliveException("Dracula is already on the board");
				
				rtr = game.addGameObject(new Dracula(x, y, game));
				game.draculaLives(); 
			}
			else if (type.equalsIgnoreCase("e")) rtr = game.addGameObject(new ExplosiveVampire(x, y, game));
			else{
				throw new CommandExecuteException("Not a valid vampire type, avaliable types are '' | 'n', 'd' or 'e'");
			} //Ya no hace falta lo chequeamos en el parseo
			
			if (rtr) {
				game.vampireSpawns();
			}
			
			return rtr;
		} catch (CommandExecuteException err){
			throw new CommandExecuteException(err.getMessage() + "\n[ERROR]: Failed to add vampire");
		}
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if ((commandWords[0].equalsIgnoreCase("vampire") || commandWords[0].equalsIgnoreCase("v"))){
			if (commandWords.length == 3)
				return new AddVampireCommand("vampire", "v", commandWords[1] + " " + commandWords[2], "[v]ampire <type> <x> <y> : Add vampire of type selected on position x, y. Possible types are 'd', 'n'|'' and 'e'", "n", 3);
			else if (commandWords[1].equalsIgnoreCase("d")|| commandWords[1].equalsIgnoreCase("n") || commandWords[1].equalsIgnoreCase("e"))
				return new AddVampireCommand("vampire", "v", commandWords[2] + " " + commandWords[3], "[v]ampire <type> <x> <y> : Add vampire of type selected on position x, y. Possible types are 'd', 'n'|'' and 'e'", commandWords[1]);
			else throw new CommandParseException("Unvalid type: [v]ampire [<type>] <x> <y>. Type = {''|'D'|'E'}");
		}
		
		return null;
	}

}
