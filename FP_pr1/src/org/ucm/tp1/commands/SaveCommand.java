package org.ucm.tp1.commands;

import java.io.FileWriter;
import java.io.IOException;
import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.logic.Game;

public class SaveCommand extends Command{
	
	private String fileName;
	
	public SaveCommand(){
		super("", "", "", "[s]ave <filename>: save program state", 2);
	}
	
	public SaveCommand(String name, String shortcut, String details, String help) {		
		super(name, shortcut, details, help, 2);
		

		this.fileName = details;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		try{
			FileWriter file = new FileWriter(this.fileName + ".dat");
		    file.write("Buffy the Vampire Slayer v3.0\n\n" + game.serialize());
		    file.close();
		    System.out.println("Game successfully saved to file " + this.fileName + ".dat");
		} catch (IOException e){
			throw new CommandExecuteException("Unable to save game state (file error)");
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if ((commandWords[0].equalsIgnoreCase("save") || commandWords[0].equalsIgnoreCase("s"))) {
			return new SaveCommand("save", "s", commandWords[1], "[s]ave <filename>: save program state");
		}
		return null;
	}

}
