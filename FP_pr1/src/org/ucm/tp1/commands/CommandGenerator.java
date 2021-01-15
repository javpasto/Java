package org.ucm.tp1.commands;

import org.ucm.tp1.exceptions.CommandParseException;

public class CommandGenerator {

	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new UpdateCommand(),
		new BankBloodCommand(),
		new GarlicPushCommand(),
		new LightFlashCommand(),
		new AddVampireCommand(),
		new SuperCoinsCommand(),
		new SerializeCommand(),
		new SaveCommand()
	};
	
	//invoca el parse de cada subclase
	public static Command parseCommand(String[] commandWords) throws CommandParseException{
		for (int i = 0; i < availableCommands.length; i++){
			try{
				Command aux = availableCommands[i].parse(commandWords);
				if (aux != null) {
					if (commandWords.length != 1) return availableCommands[i].numArgumentsCommand(commandWords, aux);
					return aux;
				}
			} catch (NumberFormatException ex){
				throw new CommandParseException("Only numbers are accepted as position.");
			} catch (ArrayIndexOutOfBoundsException ex){
				throw new CommandParseException("Incorrect number of arguments. " + availableCommands[i].helpText());
			}
		}
		
		throw new CommandParseException("Unknown Command.");
	}
	
	public static void helpMessage(){
		System.out.println("available commands:");
		for (int i = 0; i < availableCommands.length; i++){
			System.out.println(availableCommands[i].helpText());
		}
	}
	
}
