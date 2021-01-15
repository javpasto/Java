package org.ucm.tp1.control;

import java.util.Scanner;

import org.ucm.tp1.commands.Command;
import org.ucm.tp1.commands.CommandGenerator;
import org.ucm.tp1.exceptions.GameException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.view.GamePrinter;

public class Controller {

	public final String prompt = "Command > ";
	public static final String unknownCommandMsg = String.format("Unknown command");

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(new GamePrinter(this.game, this.game.getX(), this.game.getY()));
   }

	public void run() {
    	
    	boolean refreshDisplay = true;
    	
    	while (!game.isFinished()){
    		
    		if (refreshDisplay) printGame();
    		refreshDisplay = false;
    		
    		System.out.println(prompt);
    		String s = scanner.nextLine();
    		String[] parameters = s.toLowerCase().trim().split(" ");
    		System.out.println("[DEBUG] Executing: " + s);
    		try{
    			Command command = CommandGenerator.parseCommand(parameters);
    			refreshDisplay = command.execute(game);
    		} catch(GameException ex){
    			System.out.format("[ERROR]: " + ex.getMessage() + " %n %n");
    		}
    	}
    	
    	//System.out.println(new GamePrinter(this.game, this.game.getX(), this.game.getY()));
    	
    }

}

