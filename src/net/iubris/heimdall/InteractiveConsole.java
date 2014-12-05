/**
 * inspired by http://littletutorials.com/2008/03/14/console-applications-with-java-6/
 */
package net.iubris.heimdall;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.iubris.heimdall.actions.CommandAction;
import net.iubris.heimdall.command.ConsoleCommand;
import net.iubris.heimdall.listeners.CommandExceptionListener;

public class InteractiveConsole {
	
	private static final String UNKNOWN_COMMAND = "Unknown command [%1$s]%n";
	private static final String PROMPT = "facri> ";
	
	private final Map<Enum<? extends ConsoleCommand> ,CommandAction> actionsMap = new HashMap<>();

//	@Inject
	public InteractiveConsole(CommandAction... commandActions) {
//		for (CommandAction commandAction: commandActions)
//			actionsMap.put(commandAction.getCommand().asString(),commandAction);
		
	}
	
	public InteractiveConsole(CommandWithAction... commandsWithActions) {
		
//actionsMap.put(WorldTargetChar.f, null	);
		
//		ConsoleCommand consoleCommand;
//		for(Enum<? extends ConsoleCommand> consolecommandEnum: actionsMap.keySet()) {
//			try {
//				consoleCommand = Enum.valueOf(consolecommandEnum.getDeclaringClass(), "asd");
//			break;
//			} catch(IllegalArgumentException e) {
//				
//			}
//		}
		for (CommandWithAction cwa: commandsWithActions) {
			actionsMap.put(cwa.getConsoleCommand(), cwa.getCommandAction());
		}
	}
	
	public void execCommandLoop(final Console console) {
		while (true) {
			String commandLine = console.readLine(PROMPT);
			Scanner scanner = new Scanner(commandLine);

			if (scanner.hasNext()) {
//				final String commandName = scanner.next().toUpperCase();
				String line = scanner.nextLine();
				String[] splittedLine = line.split(" ");
				final String command = splittedLine[0];

				CommandExceptionListener exceptionListener = null;
				try {
//					String commandChar = commandName/*.substring(0, 1)*//*.toUpperCase()*/;
//					final Command command = Enum.valueOf(Command.class, commandChar);
					
					// old
//					if (!actionsMap.containsKey(command)) {						
//						console.printf(UNKNOWN_COMMAND, command);
//						continue;
//					}
					ConsoleCommand consoleCommand = null;
					for(Enum<? extends ConsoleCommand> consolecommandEnum: actionsMap.keySet()) {
						try {
							consoleCommand = Enum.valueOf(consolecommandEnum.getDeclaringClass(), "asd");
							break; // found
						} catch(IllegalArgumentException e) {}
					}
					if (consoleCommand==null) {
//						console.printf(UNKNOWN_COMMAND);
						console.printf(UNKNOWN_COMMAND, command);
						continue;
					}
					
					
//					} else {
//					String param = scanner.hasNext() ? scanner.next() : null;
//					String params = null;
					List<String> argumentsList = new ArrayList<>(0);
					if (splittedLine.length>1) {
						argumentsList.addAll( Arrays.asList( splittedLine ) );
						argumentsList.remove(0);						
					}
					
					exceptionListener = new CommandExceptionListener(console, consoleCommand);
					/*command.exec(console, new String[] { param }, 
//							new Listener() {
//						@Override
//						public void exception(Exception e) {
//							console.printf(COMMAND_ERROR, cmd, e.getMessage());
//						}
//					}
							commandListener);*/
						
	//					System.out.println(commandChar);
					
					
					
					CommandAction action = actionsMap.get( consoleCommand );
	//					System.out.println(action);
					action.exec(console, argumentsList);
					
						
	//					OptionArgument optionArgument = Enum.valueOf(OptionArgument.class, commandLine);
	//					optionArgument.doCommand();
//					}
//				} catch (IllegalArgumentException e) {
//					console.printf(UNKNOWN_COMMAND, commandName);
				} catch (Exception e) {
					exceptionListener.handleException(e);
					e.printStackTrace();
				}
			}
			scanner.close();
		}// end while
	}// end method
}
