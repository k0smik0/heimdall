/**
 * inspired by http://littletutorials.com/2008/03/14/console-applications-with-java-6/
 */
package net.iubris.heimdall;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import net.iubris.heimdall.actions.CommandAction;
import net.iubris.heimdall.actions.HelpAction;
import net.iubris.heimdall.actions.QuitAction;
import net.iubris.heimdall.actions.QuitAction.PreExit;
import net.iubris.heimdall.command.ConsoleCommand;
import net.iubris.heimdall.listeners.CommandExceptionListener;

public class InteractiveConsole {
	
	private static final String UNKNOWN_COMMAND = "Unknown command [%1$s]%n";
	private static final String PROMPT = "facri> ";
	
	private final Map<Enum<? extends ConsoleCommand>, CommandAction> actionsMap = new LinkedHashMap<>();
	private final String title;
	private QuitAction quitAction;
	
	public InteractiveConsole(String title, Set<CommandWithAction> commandsWithActions, Set<PreExit> preExits) {
		this.title = "'"+title+"' available commands:";
		for (CommandWithAction commandWithAction: commandsWithActions) {
			actionsMap.put(commandWithAction.getConsoleCommand(), commandWithAction.getCommandAction());
		}
		quitAction = new QuitAction(preExits);
		actionsMap.put(QuitAction.QuitCommand.Q, quitAction);
	}
	
	public InteractiveConsole(String title, CommandWithAction commandWithAction, PreExit preExit) {
		this.title = "'"+title+"' available commands:";
//		for (CommandWithAction commandWithAction: commandsWithActions) {
			actionsMap.put(commandWithAction.getConsoleCommand(), commandWithAction.getCommandAction());
//		}
		quitAction = new QuitAction(preExit);
		actionsMap.put(QuitAction.QuitCommand.Q, quitAction);
	}
	
	public InteractiveConsole(String title, CommandWithAction... commandsWithActions) {
		this.title = "'"+title+"' available commands:";
		for (CommandWithAction commandWithAction: commandsWithActions) {
			actionsMap.put(commandWithAction.getConsoleCommand(), commandWithAction.getCommandAction());
		}
		quitAction = new QuitAction();
		actionsMap.put(QuitAction.QuitCommand.Q, quitAction);
	}
	
	public void addPreExit(PreExit preExit) {
		quitAction.addPreExit(preExit);
	}
	
	
	
	public void execCommandLoop(final Console console) {
		HelpAction helpAction = new HelpAction(title, actionsMap.keySet() );
		actionsMap.put(HelpAction.HelpCommand.H, helpAction);
		helpAction.exec(console);
//		System.out.println("");
//		console.printf("\n");
		
		while (true) {
			String commandLine = console.readLine(PROMPT);
			Scanner scanner = new Scanner(commandLine);

			if (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] splittedLine = line.split(" ");
				final String command = splittedLine[0].toUpperCase();

				CommandExceptionListener exceptionListener = null;
				try {
					ConsoleCommand consoleCommand = null;
					for(Enum<? extends ConsoleCommand> consolecommandEnum: actionsMap.keySet()) {
						try {
							consoleCommand = Enum.valueOf(consolecommandEnum.getDeclaringClass(), command);
							break; // found
						} catch(IllegalArgumentException e) {}
					}
					if (consoleCommand==null) {
						console.printf(UNKNOWN_COMMAND, command);
						continue;
					}
					
					List<String> argumentsList = new ArrayList<>(0);
					if (splittedLine.length>1) {
						argumentsList.addAll( Arrays.asList( splittedLine ) );
						ListIterator<String> listIterator = argumentsList.listIterator();
						listIterator.next();
						listIterator.remove();
					}
					
					exceptionListener = new CommandExceptionListener(console, consoleCommand);
					
					CommandAction action = actionsMap.get( consoleCommand );
					action.exec(console, argumentsList.toArray( new String[0]) );
						
				} catch (Exception e) {
					exceptionListener.handleException(e);
//					e.printStackTrace();
				}
			}
			scanner.close();
		}// end while
	}// end method
	
//	@FunctionalInterface
//	public interface PreExit {
//		void doPreExiting();
//	}
}
